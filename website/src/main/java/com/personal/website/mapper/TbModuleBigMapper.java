package com.personal.website.mapper;

import com.personal.website.pojo.TbModuleBig;
import com.personal.website.pojo.TbModuleBigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbModuleBigMapper {
    int countByExample(TbModuleBigExample example);

    int deleteByExample(TbModuleBigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbModuleBig record);

    int insertSelective(TbModuleBig record);

    List<TbModuleBig> selectByExample(TbModuleBigExample example);

    TbModuleBig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbModuleBig record, @Param("example") TbModuleBigExample example);

    int updateByExample(@Param("record") TbModuleBig record, @Param("example") TbModuleBigExample example);

    int updateByPrimaryKeySelective(TbModuleBig record);

    int updateByPrimaryKey(TbModuleBig record);
}