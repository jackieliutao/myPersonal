package com.dongpo.mapper;

import com.dongpo.domain.Study;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudyMapper {
    int deleteByPrimaryKey(Integer sId);

    int insert(Study record);

    Study selectByPrimaryKey(Integer sId);

    List<Study> selectAll(@Param("key") String key);

    int updateByPrimaryKey(Study record);
}