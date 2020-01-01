package com.dongpo.service;


import com.dongpo.domain.Department;
import com.dongpo.domain.Position;
import com.dongpo.vo.PageResultVo;

import java.util.List;

public interface DepartmentService {
    PageResultVo pageList(Integer page, Integer limit,String key);

    void add(Department department);

    void delete(Integer depId);

    void deleteAll(Integer[] depIds);

    void update(Department department);

    List<Department> getAll();


}
