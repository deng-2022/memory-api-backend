<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/com/memory/memory_api/controller/InterfaceInfoController.java
=======
>>>>>>> dd943cf (重构 抽取公共服务)
package com.memory.memory_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.memorycommen.model.entity.InterfaceInfo;
import com.example.memorycommen.model.entity.User;
import com.google.gson.Gson;
import com.memory.clientsdk.client.MemoryClient;
import com.memory.memory_api.annotation.AuthCheck;
import com.memory.memory_api.common.*;
import com.memory.memory_api.constant.UserConstant;
import com.memory.memory_api.exception.BusinessException;
import com.memory.memory_api.exception.ThrowUtils;
import com.memory.memory_api.model.dto.interfaceInfo.InterFaceInfoInvokeRequest;
import com.memory.memory_api.model.dto.interfaceInfo.InterfaceInfoAddRequest;
import com.memory.memory_api.model.dto.interfaceInfo.InterfaceInfoQueryRequest;
import com.memory.memory_api.model.dto.interfaceInfo.InterfaceInfoUpdateRequest;
import com.memory.memory_api.model.enums.InterfaceInfoStatusEnum;
import com.memory.memory_api.service.InterfaceInfoService;
import com.memory.memory_api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
<<<<<<< HEAD
=======
package com.yupi.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.yupi.springbootinit.annotation.AuthCheck;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.DeleteRequest;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.constant.UserConstant;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.model.dto.interfaceInfo.InterfaceInfoAddRequest;
import com.yupi.springbootinit.model.dto.interfaceInfo.InterfaceInfoUpdateRequest;
import com.yupi.springbootinit.model.dto.interfaceInfo.InterfaceInfoQueryRequest;
import com.yupi.springbootinit.model.entity.InterfaceInfo;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.service.InterfaceInfoService;
import com.yupi.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
>>>>>>> b1096f4 (模拟接口初步实现):src/main/java/com/yupi/springbootinit/controller/InterfaceInfoController.java
=======
>>>>>>> dd943cf (重构 抽取公共服务)
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 */
@RestController
@RequestMapping("/interfaceInfo")
@Slf4j
public class InterfaceInfoController {
    @Resource
    private InterfaceInfoService interfaceInfoService;
<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/com/memory/memory_api/controller/InterfaceInfoController.java
    @Resource
    private MemoryClient memoryClient;
=======

>>>>>>> b1096f4 (模拟接口初步实现):src/main/java/com/yupi/springbootinit/controller/InterfaceInfoController.java
=======
    @Resource
    private MemoryClient memoryClient;
>>>>>>> dd943cf (重构 抽取公共服务)
    @Resource
    private UserService userService;

    private final static Gson GSON = new Gson();

    /**
     * 创建接口
     *
     * @param interfaceInfoAddRequest 创建接口参数
     * @param request                 request
     * @return 创建接口成功
     */
    @PostMapping("/add")
    public BaseResponse<Long> addInterfaceInfo(@RequestBody InterfaceInfoAddRequest interfaceInfoAddRequest,
                                               HttpServletRequest request) {
<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/com/memory/memory_api/controller/InterfaceInfoController.java
        // controller校验参数
=======
>>>>>>> b1096f4 (模拟接口初步实现):src/main/java/com/yupi/springbootinit/controller/InterfaceInfoController.java
=======
        // controller校验参数
>>>>>>> dd943cf (重构 抽取公共服务)
        if (interfaceInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoAddRequest, interfaceInfo);
        interfaceInfoService.addInterfaceInfo(interfaceInfo);

        User loginUser = userService.getLoginUser(request);
        interfaceInfo.setUserId(loginUser.getId());

        boolean result = interfaceInfoService.save(interfaceInfo);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

        long newInterfaceInfoId = interfaceInfo.getId();
        return ResultUtils.success(newInterfaceInfoId);
    }

    /**
     * 删除接口
     *
     * @param deleteRequest 删除接口参数
     * @param request       request
     * @return 删除接口成功
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteInterfaceInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/com/memory/memory_api/controller/InterfaceInfoController.java
        // controller校验参数
=======
>>>>>>> b1096f4 (模拟接口初步实现):src/main/java/com/yupi/springbootinit/controller/InterfaceInfoController.java
=======
        // controller校验参数
>>>>>>> dd943cf (重构 抽取公共服务)
        if (deleteRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();

        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        ThrowUtils.throwIf(oldInterfaceInfo == null, ErrorCode.NOT_FOUND_ERROR);

        // 仅本人或管理员可删除
        if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        boolean b = interfaceInfoService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新接口（仅管理员）
     *
     * @param interfaceInfoUpdateRequest 更新接口参数
     * @return 更新接口成功
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateInterfaceInfo(@RequestBody InterfaceInfoUpdateRequest interfaceInfoUpdateRequest) {
<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/com/memory/memory_api/controller/InterfaceInfoController.java
        // controller校验参数
=======
>>>>>>> b1096f4 (模拟接口初步实现):src/main/java/com/yupi/springbootinit/controller/InterfaceInfoController.java
=======
        // controller校验参数
>>>>>>> dd943cf (重构 抽取公共服务)
        if (interfaceInfoUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoUpdateRequest, interfaceInfo);
        interfaceInfoService.updateInterfaceInfo(interfaceInfo);

        long id = interfaceInfoUpdateRequest.getId();
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        ThrowUtils.throwIf(oldInterfaceInfo == null, ErrorCode.NOT_FOUND_ERROR);

        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResultUtils.success(result);
    }

    /**
     * 根据id获取接口信息
     *
     * @param id 接口id
     * @return 接口信息
     */
    @GetMapping("/get")
    public BaseResponse<InterfaceInfo> getInterfaceInfoById(long id, HttpServletRequest request) {
<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/com/memory/memory_api/controller/InterfaceInfoController.java
        // controller校验参数
=======
>>>>>>> b1096f4 (模拟接口初步实现):src/main/java/com/yupi/springbootinit/controller/InterfaceInfoController.java
=======
        // controller校验参数
>>>>>>> dd943cf (重构 抽取公共服务)
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);

        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        return ResultUtils.success(interfaceInfo);
    }

    /**
     * 获取接口信息列表
     * 分页查询
     *
     * @param interfaceInfoQueryRequest 查询参数
     * @param request                   request
     * @return 接口信息列表
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<InterfaceInfo>> listInterfaceInfoByPage(@RequestBody InterfaceInfoQueryRequest interfaceInfoQueryRequest,
                                                                     HttpServletRequest request) {
<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/com/memory/memory_api/controller/InterfaceInfoController.java
        // controller校验参数
=======
>>>>>>> b1096f4 (模拟接口初步实现):src/main/java/com/yupi/springbootinit/controller/InterfaceInfoController.java
=======
        // controller校验参数
>>>>>>> dd943cf (重构 抽取公共服务)
        if (interfaceInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long current = interfaceInfoQueryRequest.getCurrent();
        long size = interfaceInfoQueryRequest.getPageSize();

        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoQueryRequest, interfaceInfo);
        interfaceInfoService.addInterfaceInfo(interfaceInfo);

        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);

        Page<InterfaceInfo> interfaceInfoPage = interfaceInfoService.page(new Page<>(current, size));
        return ResultUtils.success(interfaceInfoPage);
    }
<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/com/memory/memory_api/controller/InterfaceInfoController.java
=======
>>>>>>> dd943cf (重构 抽取公共服务)

    /**
     * 发布
     *
     * @param idRequest
     * @param request
     * @return
     */
    @PostMapping("/online")
    public BaseResponse<Boolean> onlineInterfaceInfo(@RequestBody IdRequest idRequest,
                                                     HttpServletRequest request) {
        // controller校验参数
        if (idRequest == null || idRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id = idRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 判断该接口是否可以调用
        com.memory.clientsdk.model.User user = new com.memory.clientsdk.model.User();
        user.setName("test");
        String username = memoryClient.getUserByPost(user);
        if (StringUtils.isBlank(username)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "接口验证失败");
        }
        // 仅本人或管理员可修改
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        interfaceInfo.setId(id);
        interfaceInfo.setStatus(InterfaceInfoStatusEnum.ONLINE.getValue());

        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResultUtils.success(result);
    }

    /**
     * 下线
     *
     * @param idRequest
     * @param request
     * @return
     */
    @PostMapping("/offline")
    public BaseResponse<Boolean> offlineInterfaceInfo(@RequestBody IdRequest idRequest,
                                                      HttpServletRequest request) {
        // controller校验参数
        if (idRequest == null || idRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id = idRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        interfaceInfo.setId(id);
        interfaceInfo.setStatus(InterfaceInfoStatusEnum.OFFLINE.getValue());
        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResultUtils.success(result);
    }

    /**
     * 接口调用
     *
     * @param interFaceInfoInvokeRequest 接口调用参数
     * @param request                    request
     * @return
     */
    @PostMapping("/invoke")
    public BaseResponse<Object> invokeInterfaceInfo(@RequestBody InterFaceInfoInvokeRequest interFaceInfoInvokeRequest,
                                                    HttpServletRequest request) {
        // controller校验参数
        if (interFaceInfoInvokeRequest == null || interFaceInfoInvokeRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id = interFaceInfoInvokeRequest.getId();
        String userRequestParams = interFaceInfoInvokeRequest.getUserRequestParams();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 判断是否状态是否正常
        if (oldInterfaceInfo.getStatus().equals(InterfaceInfoStatusEnum.OFFLINE.getValue())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口已关闭");
        }
        // 用户调用接口
        User LoginUser = userService.getLoginUser(request);
        String accessKey = LoginUser.getAccessKey();
        String secretKey = LoginUser.getSecretKey();

        MemoryClient tempClient = new MemoryClient(accessKey, secretKey);
        Gson gson = new Gson();
        com.memory.clientsdk.model.User user = gson.fromJson(userRequestParams, com.memory.clientsdk.model.User.class);
        //TODO 根据不同地址调用对应接口
        String usernameByPost = tempClient.getUserByPost(user);
        return ResultUtils.success(usernameByPost);
    }
<<<<<<< HEAD
=======
>>>>>>> b1096f4 (模拟接口初步实现):src/main/java/com/yupi/springbootinit/controller/InterfaceInfoController.java
=======
>>>>>>> dd943cf (重构 抽取公共服务)
}
