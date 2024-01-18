package com.memory.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.memory.common.model.entity.InterfaceInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Lenovo
 * @description 针对表【interface_info(接口信息表)】的数据库操作Mapper
 * @createDate 2023-07-19 08:17:01
 * @Entity com.memory.api.model.entity.InterfaceInfo
 */
public interface InterfaceInfoMapper extends BaseMapper<InterfaceInfo> {
    @Select("select id from interface_info")
    List<Long> getInterfaceInfoIdList();
}




