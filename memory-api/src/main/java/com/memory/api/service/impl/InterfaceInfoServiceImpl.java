package com.memory.api.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memory.common.model.entity.InterfaceInfo;
import com.google.gson.Gson;
import com.memory.common.common.ErrorCode;
import com.memory.api.exception.BusinessException;
import com.memory.api.service.UserService;
import com.memory.api.mapper.InterfaceInfoMapper;
import com.memory.api.service.InterfaceInfoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 帖子服务实现
 *
 * @author memory
 */
@Service
@Slf4j
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo> implements InterfaceInfoService {
    private final static Gson GSON = new Gson();

    @Resource
    private UserService userService;

    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_DESCRIPTION_LENGTH = 256;
    private static final int MAX_URL_LENGTH = 512;
    private static final int MAX_METHOD_LENGTH = 256;

    /**
     * 新增接口校验
     *
     * @param interfaceInfo 新增接口参数
     */
    @Override
    public void addInterfaceInfo(InterfaceInfo interfaceInfo) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String url = interfaceInfo.getUrl();
        String method = interfaceInfo.getMethod();

        // 校验名称
        if (StringUtils.isNotBlank(name) && name.length() > MAX_NAME_LENGTH) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称不符合要求");
        }

        // 校验描述
        if (StringUtils.isNotBlank(description) && description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "描述不符合要求");
        }

        // 校验接口地址
        if (StringUtils.isNotBlank(url) && url.length() > MAX_URL_LENGTH) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口地址不符合要求");
        }

        // 校验请求类型
        if (StringUtils.isNotBlank(method) && method.length() > MAX_METHOD_LENGTH) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求类型不符合要求");
        }

        // 校验其他字段...
    }

    @Override
    public void updateInterfaceInfo(InterfaceInfo interfaceInfo) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Long id = interfaceInfo.getId();
        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String url = interfaceInfo.getUrl();
        String method = interfaceInfo.getMethod();

        // 校验名称
        if (id == null || id < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id不符合要求");
        }

        // 校验名称
        if (StringUtils.isNotBlank(name) && name.length() > MAX_NAME_LENGTH) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称不符合要求");
        }

        // 校验描述
        if (StringUtils.isNotBlank(description) && description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "描述不符合要求");
        }

        // 校验接口地址
        if (StringUtils.isNotBlank(url) && url.length() > MAX_URL_LENGTH) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口地址不符合要求");
        }

        // 校验请求类型
        if (StringUtils.isNotBlank(method) && method.length() > MAX_METHOD_LENGTH) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求类型不符合要求");
        }

        // 校验其他字段...
    }
}




