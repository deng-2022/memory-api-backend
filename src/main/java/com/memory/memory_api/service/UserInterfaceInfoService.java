package com.memory.memory_api.service;

import com.example.memorycommen.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Lenovo
 * @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
 * @createDate 2023-08-23 14:29:17
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    boolean invokeCount(long interfaceInfoId, long userId);

}
