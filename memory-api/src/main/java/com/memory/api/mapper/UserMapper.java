package com.memory.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.memory.common.model.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户数据库操作
 *
 * @author <a href="https://gitee.com/deng-2022">回忆如初</a>
 * @from <a href="https://deng-2022.gitee.io/blog/">Memory's Blog</a>
 */
public interface UserMapper extends BaseMapper<User> {
    @Select(value = "select * from user where userRole = 'admin'")
    List<User> userList();

    List<User> userList2(String role);

}




