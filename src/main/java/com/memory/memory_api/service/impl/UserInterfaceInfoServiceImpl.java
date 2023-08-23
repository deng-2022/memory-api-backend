package com.memory.memory_api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.memorycommen.model.entity.UserInterfaceInfo;
import com.memory.memory_api.service.UserInterfaceInfoService;
import com.memory.memory_api.mapper.UserInterfaceInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2023-08-23 14:29:17
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{

}




