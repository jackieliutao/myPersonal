package com.dongpo.mapper;

import com.dongpo.domain.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer depId);

    Department selectByPrimaryKey(Integer depId);

    List<Department> selectAll(@Param("key") String key);

    int updateByPrimaryKey(Department record);

    void add(Department department);

    List<Department> getAll();
}