package com.dongpo.mapper;

import com.dongpo.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    Employee selectByPrimaryKey(Integer empId);

    List<Employee> selectAll(@Param("depId") Integer depId,@Param("poId") Integer poId,@Param("key") String key);

    int updateByPrimaryKey(Employee record);

    List<Employee> getAll();

    void updateRelation(Integer pid);
}