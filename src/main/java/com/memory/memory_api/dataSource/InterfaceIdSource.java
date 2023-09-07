package com.memory.memory_api.dataSource;

import com.google.gson.Gson;
import com.memory.clientsdk.client.MemoryClient;
import com.memory.memory_api.common.ErrorCode;
import com.memory.memory_api.exception.ThrowUtils;
import com.memory.memory_api.mapper.InterfaceInfoMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 邓哈哈
 * 2023/9/5 23:08
 * Function:
 * Version 1.0
 */
@Data
@Component
public class InterfaceIdSource {
    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    /**
     * 接口id->接口调用逻辑
     */
    private List<Long> interfaceIdList = new ArrayList<>();

    private String userRequestParams;

    /**
     * 初始化interfaceInfoIdList
     */
    public void doInit() {
        interfaceIdList = interfaceInfoMapper.getInterfaceInfoIdList();
    }

    /**
     * 调用接口
     *
     * @param id 接口id
     */
    public String invokeInterfaceById(Long id, String userRequestParams, MemoryClient tempClient) {
        // 初始化interfaceInfoIdList
//        this.doInit();
        List<Long> interfaceInfoIdList = interfaceInfoMapper.getInterfaceInfoIdList();
        // 处理其他未匹配到的情况
        ThrowUtils.throwIf(!interfaceInfoIdList.contains(id), ErrorCode.NOT_FOUND_ERROR, "该接口不存在或已被禁用");
        // 匹配成功，调用接口
        this.userRequestParams = userRequestParams;
        Gson gson = new Gson();
        String result = "";
        if (id == 1646372563419L) {
            com.memory.clientsdk.model.User user = gson.fromJson(userRequestParams, com.memory.clientsdk.model.User.class);
            result = tempClient.getUserByPost(user);
        } else if (id == 1646335784547L) {
            com.memory.clientsdk.model.Words words = gson.fromJson(userRequestParams, com.memory.clientsdk.model.Words.class);
            result = tempClient.getRandomWord(words);
        }
        return result;
    }
}
