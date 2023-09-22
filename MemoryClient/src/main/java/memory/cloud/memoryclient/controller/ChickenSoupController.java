package memory.cloud.memoryclient.controller;

import memory.cloud.memoryclient.domain.Words;
import memory.cloud.memoryclient.mapper.WordsMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 邓哈哈
 * 2023/7/28 22:52
 * Function: 随机生成毒鸡汤
 * Version 1.0
 */

@RestController
@RequestMapping("/soup")
public class ChickenSoupController {
    @Resource
    private WordsMapper wordsMapper;

    @PostMapping("/one/random")
    public Words getRandomWord(@RequestBody Words words) {
        Integer type = words.getType();
        return wordsMapper.getRandomWord(type);
    }

}
