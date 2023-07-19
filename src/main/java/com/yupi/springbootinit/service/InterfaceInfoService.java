package com.yupi.springbootinit.service;

import com.yupi.springbootinit.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Lenovo
* @description 针对表【interface_info(接口信息表)】的数据库操作Service
* @createDate 2023-07-19 08:17:01
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
