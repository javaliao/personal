package com.personal.website.mapper;

import com.personal.website.pojo.TbPhotos;
import com.personal.website.pojo.TbPhotosExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbPhotosMapper {
    int countByExample(TbPhotosExample example);

    int deleteByExample(TbPhotosExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbPhotos record);

    int insertSelective(TbPhotos record);

    List<TbPhotos> selectByExample(TbPhotosExample example);

    TbPhotos selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbPhotos record, @Param("example") TbPhotosExample example);

    int updateByExample(@Param("record") TbPhotos record, @Param("example") TbPhotosExample example);

    int updateByPrimaryKeySelective(TbPhotos record);

    int updateByPrimaryKey(TbPhotos record);
}