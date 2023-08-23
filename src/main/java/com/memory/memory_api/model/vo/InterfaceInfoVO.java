package com.memory.memory_api.model.vo;

<<<<<<< HEAD
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

=======
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 接口信息表
 * @TableName interface_info
 */
@TableName(value ="interface_info")
@Data
public class InterfaceInfoVO implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 接口地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * 请求头
     */
    @TableField(value = "requestHeader")
    private String requestHeader;

    /**
     * 响应头
     */
    @TableField(value = "responseHeader")
    private String responseHeader;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 创建人
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 接口状态（0 - 关闭， 1 - 开启））
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 请求类型
     */
    @TableField(value = "method")
    private String method;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    /**
     * 是否删除(0-未删, 1-已删)
     */
    @TableField(value = "isDelete")
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
>>>>>>> e32758e (重构 抽取公共服务)
    private static final long serialVersionUID = 1L;
}