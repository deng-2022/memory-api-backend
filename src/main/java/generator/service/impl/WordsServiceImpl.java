package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Words;
import generator.service.WordsService;
import generator.mapper.WordsMapper;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【words(名言警句)】的数据库操作Service实现
* @createDate 2023-09-05 15:21:55
*/
@Service
public class WordsServiceImpl extends ServiceImpl<WordsMapper, Words>
    implements WordsService{

}




