package com.dongpo.service;


import com.dongpo.domain.Employee;
import com.dongpo.domain.Family;
import com.dongpo.vo.PageResultVo;

import java.text.ParseException;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    PageResultVo pageList(Integer page, Integer pageSize,Integer depId,Integer poId,String key);

    void add(Employee employee, Family family) throws ParseException;

    void deleteAll(Integer[] empIds,String path);

    void delete(Integer empId, String realPath);

    void update(Employee employee,Family family) throws ParseException;

    Family getFamily(Integer empId);

    Employee findOne(Integer empId);
}
