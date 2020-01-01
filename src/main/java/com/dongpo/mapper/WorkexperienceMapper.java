package com.dongpo.mapper;

import com.dongpo.domain.Workexperience;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkexperienceMapper {
    int deleteByPrimaryKey(Integer wbId);

    int insert(Workexperience record);

    Workexperience selectByPrimaryKey(Integer wbId);

    List<Workexperience> selectAll(@Param("key") String key);

    int updateByPrimaryKey(Workexperience record);
}