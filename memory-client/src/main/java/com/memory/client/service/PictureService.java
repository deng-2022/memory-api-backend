package com.memory.client.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.client.model.domain.Picture;

import java.io.IOException;

/**
 * 图片爬取服务
 */
public interface PictureService {
    Page<Picture> listPictureVOByPage(String category) throws IOException;
}
