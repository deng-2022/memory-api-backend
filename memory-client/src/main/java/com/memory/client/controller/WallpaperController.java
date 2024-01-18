package com.memory.client.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.common.common.BaseResponse;
import com.memory.common.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import com.memory.client.model.domain.Picture;
import com.memory.client.service.PictureService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author 邓哈哈
 * 2023/9/19 23:12
 * Function: 随机生成壁纸(提供预览和下载)
 * Version 1.0
 */

@RestController
@RequestMapping("/wallpaper")
@Slf4j
public class WallpaperController {
    @Resource
    private PictureService pictureService;

    @PostMapping("/list/page/vo")
    public BaseResponse<Page<Picture>> listPictureByPage(@RequestBody Picture picture) throws IOException {
        // controller层对参数的校验
        String category = picture.getCategory();

        Page<Picture> picturePage = pictureService.listPictureVOByPage(category);
        return ResultUtils.success(picturePage);
    }
}
