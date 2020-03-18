package com.personal.website.mapper;

import com.personal.website.pojo.TbModuleSmall;
import com.personal.website.pojo.TbModuleSmallExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbModuleSmallMapper {
    int countByExample(TbModuleSmallExample example);

    int deleteByExample(TbModuleSmallExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbModuleSmall record);

    int insertSelective(TbModuleSmall record);

    List<TbModuleSmall> selectByExample(TbModuleSmallExample example);

    TbModuleSmall selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbModuleSmall record, @Param("example") TbModuleSmallExample example);

    int updateByExample(@Param("record") TbModuleSmall record, @Param("example") TbModuleSmallExample example);

    int updateByPrimaryKeySelective(TbModuleSmall record);

    int updateByPrimaryKey(TbModuleSmall record);
}