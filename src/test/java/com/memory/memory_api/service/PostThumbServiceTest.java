package com.memory.memory_api.service;

import com.example.memorycommen.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * 帖子点赞服务测试
 */
// @SpringBootTest
class PostThumbServiceTest {

    @Resource
    private PostThumbService postThumbService;

    private static final User loginUser = new User();

    @BeforeAll
    static void setUp() {
        loginUser.setId(1L);
    }

    @Test
    void doPostThumb() {
        int i = postThumbService.doPostThumb(1L, loginUser);
        Assertions.assertTrue(i >= 0);
    }
}
