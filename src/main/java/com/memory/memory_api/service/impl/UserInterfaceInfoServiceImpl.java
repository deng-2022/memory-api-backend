package com.memory.memory_api.service.impl;

<<<<<<< HEAD
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.memorycommen.model.entity.UserInterfaceInfo;
import com.memory.memory_api.common.ErrorCode;
import com.memory.memory_api.exception.BusinessException;
import com.memory.memory_api.mapper.UserInterfaceInfoMapper;
import com.memory.memory_api.service.UserInterfaceInfoService;
=======
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.memorycommen.model.entity.UserInterfaceInfo;
import com.memory.memory_api.service.UserInterfaceInfoService;
import com.memory.memory_api.mapper.UserInterfaceInfoMapper;
>>>>>>> e32758e (重构 抽取公共服务)
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2023-08-23 14:29:17
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{

<<<<<<< HEAD
    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        // 判断
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("interfaceInfoId", 1);
        updateWrapper.eq("userId", 1);

//        updateWrapper.gt("leftNum", 0);
        updateWrapper.setSql("leftNum = leftNum - 1, totalNum = totalNum + 1");
        return this.update(updateWrapper);
    }
=======
>>>>>>> e32758e (重构 抽取公共服务)
}




