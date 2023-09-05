package com.memory.memory_api.dataSource;

import com.memory.memory_api.common.ErrorCode;
import com.memory.memory_api.exception.ThrowUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author 邓哈哈
 * 2023/9/5 23:08
 * Function:
 * Version 1.0
 */
@Component
public class InterfaceIdSource {
    /**
     * 接口id->接口调用逻辑
     */
    private static HashMap<Long, Runnable> interfaceIdMap = new HashMap<>();

    public void doInit() {
        interfaceIdMap.put(1646335784547L, () -> {

        });
        interfaceIdMap.put(1646372563419L, () -> {

        });
        interfaceIdMap.put(1646871133614L, () -> {

        });
    }

    /**
     * 调用接口
     *
     * @param id 接口id
     */
    public static void invokeInterfaceById(Long id) {
        Runnable interfaceAction = interfaceIdMap.get(id);
        // 处理其他未匹配到的情况
        ThrowUtils.throwIf(interfaceAction == null, ErrorCode.NOT_FOUND_ERROR, "该接口不存在或已被禁用");
        // 匹配成功，调用接口
        interfaceAction.run();
    }
}
