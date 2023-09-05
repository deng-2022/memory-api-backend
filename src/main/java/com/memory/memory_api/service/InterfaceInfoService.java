package com.memory.memory_api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.memorycommen.model.entity.InterfaceInfo;

/**
 * @author Lenovo
 * @description 针对表【interface_info(接口信息表)】的数据库操作Service
 * @createDate 2023-07-19 08:17:01
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    void addInterfaceInfo(InterfaceInfo interfaceInfo);
    void updateInterfaceInfo(InterfaceInfo interfaceInfo);

}
