package com.memory.client.model.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 邓哈哈
 * 2023/11/19 22:37
 * Function: 图片类
 * Version 1.0
 */

@Data
public class Picture implements Serializable {
    public Picture() {
    }

    /**
     * 所属分类
     */
    private String category;

    /**
     * 图片名
     */
    private String title;

    /**
     * 图片路径
     */
    private String url;

    private static final long serialVersionUID = 1L;
}
