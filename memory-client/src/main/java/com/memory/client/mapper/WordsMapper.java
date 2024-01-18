package com.memory.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.memory.client.model.domain.Words;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Lenovo
 * @description 针对表【words(名言警句)】的数据库操作Mapper
 * @createDate 2023-09-05 15:24:04
 * @Entity generator.domain.Words
 */
@Mapper
public interface WordsMapper extends BaseMapper<Words> {
    /**
     * 返回随机名言
     *
     * @param type 类型
     * @return 随机名言
     */
    Words getRandomWord(Integer type);
}




