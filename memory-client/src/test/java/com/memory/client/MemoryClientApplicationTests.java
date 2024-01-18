package com.memory.client;

import com.memory.clientsdk.client.MemoryClient;
import com.memory.clientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MemoryClientApplicationTests {
    @Resource
    private MemoryClient memoryClient;

    @Test
    public void testMemorySdk() {
        if (memoryClient != null) {
            System.out.println("成功了");
            memoryClient.getNameByGet("邓哈哈");
            memoryClient.getNameByPost("邓嘻嘻");

//        User user = new User();
//        user.setName("邓尼玛");
            User user = new User("邓尼玛");
            memoryClient.getUserByPost(user);
        } else {
            System.out.println("失败！");
        }
    }

    @Test
    void contextLoads() {
        System.out.println("成功了");
        memoryClient.getNameByGet("邓哈哈");
        memoryClient.getNameByPost("邓嘻嘻");

//        User user = new User();
//        user.setName("邓尼玛");
        User user = new User("邓尼玛");
        memoryClient.getUserByPost(user);
    }

}
