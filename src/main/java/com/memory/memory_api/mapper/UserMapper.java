package com.memory.memory_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.memorycommen.model.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户数据库操作
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public interface UserMapper extends BaseMapper<User> {
    @Select(value = "select * from user where userRole = 'admin'")
    List<User> userList();

    List<User> userList2(String role);

}




