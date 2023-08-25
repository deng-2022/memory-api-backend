package com.memory.memory_api.model.vo;

import com.example.memorycommen.model.entity.InterfaceInfo;
import lombok.Data;

/**
 * 接口信息表
 *
 * @TableName interface_info
 */
@Data
public class InterfaceInfoVO extends InterfaceInfo {

    /**s
     * 调用次数
     */
    private Integer totalNum;

    private static final long serialVersionUID = 1L;
}