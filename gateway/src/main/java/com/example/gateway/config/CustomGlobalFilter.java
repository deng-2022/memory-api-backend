package com.example.gateway.config;

import com.example.memorycommen.model.entity.InterfaceInfo;
import com.example.memorycommen.model.entity.User;
import com.example.memorycommen.service.InnerInterfaceInfoService;
import com.example.memorycommen.service.InnerUserInterfaceInfoService;
import com.example.memorycommen.service.InnerUserService;
import com.memory.clientsdk.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GateWay请求过滤器
 */
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @DubboReference
    private InnerUserService innerUserService;

    @DubboReference
    private InnerInterfaceInfoService innerInterfaceInfoService;

    @DubboReference
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;
    private static final List<String> IP_WHITE_HOSTS = Arrays.asList("/127.0.0.1:8090");
    private static final String INTERFACE_HOST = "http://localhost:8090";

    /**
     * 过滤请求
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 用户发送请求到 API 网关, 获取请求日志
        ServerHttpRequest request = exchange.getRequest();
        log.info("请求唯一标识:{}", request.getId());
        String path = INTERFACE_HOST + request.getPath();
        log.info("请求路径:{}", path);
        String method = request.getMethod().toString();
        log.info("请求方法:{}", method);
        log.info("请求参数:{}", request.getQueryParams());
        String sourceAddress = request.getLocalAddress().toString();
        log.info("请求来源地址:{}", sourceAddress);
        log.info("请求目标地址:{}", request.getRemoteAddress());
        ServerHttpResponse response = exchange.getResponse();

        // 2. 黑白名单
        if (!IP_WHITE_HOSTS.contains(sourceAddress)) {
            // 设置错误状态码并返回
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }

        // 4. 用户鉴权（判断 accessKey, secretKey 是否合法）
        HttpHeaders headers = request.getHeaders();

        // 设置请求编码
        MediaType requestContentType = headers.getContentType();
        if (requestContentType != null && requestContentType.getCharset() == null) {
            headers.setContentType(new MediaType(requestContentType.getType(), requestContentType.getSubtype(),
                    StandardCharsets.UTF_8));
        }

        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String body = headers.getFirst("body");
        String sign = headers.getFirst("sign");

        // 4.1.校验accessKey
        // todo 从数据库中查询, accessKey是否分配给该用户
        if (accessKey == null || !accessKey.equals("memory")) {
            return handleNoAuth(response);
        }
        // accessKey 是否分配给该用户
        User invokeUser = innerUserService.getInvokeUser(accessKey);
        if (invokeUser == null) {
            return handleNoAuth(response);
        }

        // 4.2.校验 nonce 不能超过10000
        if (nonce == null || Long.parseLong(nonce) > 10000) {
            throw new RuntimeException("无权限");
        }
        // 4.3.校验 timestamp 不能超时5分钟
        final long FIVE_MINUTES = 60L * 5L;
        if (timestamp == null || System.currentTimeMillis() / 1000L - Long.parseLong(timestamp) >= FIVE_MINUTES) {
            return handleNoAuth(response);
        }
        // 4.4.校验body
        if (body == null) return handleNoAuth(response);

        // 4.5.校验 secretKey
        // 从数据库中查询, secretKey是否分配给该用户
        String secretKey = invokeUser.getSecretKey();

        if (secretKey == null) {
            return handleNoAuth(response);
        }

        String serverSign = SignUtils.getSign(body, secretKey);
        if (sign == null || !sign.equals(serverSign)) {
            return handleNoAuth(response);
        }

        // 5. 请求的模拟接口是否存在
        // todo 从数据库中查询请求调用的接口是否存在 请求方法是否匹配 请求参数是否符合要求等
        InterfaceInfo interfaceInfo = innerInterfaceInfoService.getInterfaceInfo(path, method);
        if (interfaceInfo == null) {
            return handleNoAuth(response);
        }

        // 6.调用接口
        return handleResponse(exchange, chain, interfaceInfo.getId(), invokeUser.getId());
    }

    /**
     * Response 请求处理
     *
     * @param exchange exchange
     * @param chain    chain
     * @return 接口响应
     */
    public Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain, long interfaceInfoId, long userId) {
        try {
            ServerHttpResponse originalResponse = exchange.getResponse();
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();

            HttpStatus statusCode = originalResponse.getStatusCode();

            if (statusCode == HttpStatus.OK) {
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        // log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            return super.writeWith(fluxBody.map(dataBuffer -> {
                                // todo 调用成功, 次数 + 1
                                // 7. 调用成功，接口调用次数 + 1 invokeCount
                                try {
                                    innerUserInterfaceInfoService.invokeCount(interfaceInfoId, userId);
                                } catch (Exception e) {
                                    log.error("invokeCount error", e);
                                }
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                DataBufferUtils.release(dataBuffer);// 释放掉内存
                                // 构建日志
                                StringBuilder sb2 = new StringBuilder(200);
                                sb2.append("<--- {} {} \n");
                                List<Object> rspArgs = new ArrayList<>();
                                rspArgs.add(originalResponse.getStatusCode());
                                // rspArgs.add(requestUrl);
                                String data = new String(content, StandardCharsets.UTF_8);// data
                                sb2.append(data);
                                log.info(sb2.toString(), rspArgs.toArray());// log.info("<-- {} {}\n", originalResponse.getStatusCode(), data);
                                return bufferFactory.wrap(content);
                            }));
                        } else {
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange);// 降级处理返回数据
        } catch (Exception e) {
            log.error("gateway log exception.\n" + e);
            return chain.filter(exchange);
        }
    }

    /**
     * 判断接口调用权限
     *
     * @param response 响应
     * @return 返回响应
     */
    public Mono<Void> handleNoAuth(ServerHttpResponse response) {
        // 设置状态码并返回
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    /**
     * 获取请求优先级
     *
     * @return 返回优先级
     */
    @Override
    public int getOrder() {
        // 设置过滤器的优先级
        return Ordered.HIGHEST_PRECEDENCE;
    }
}