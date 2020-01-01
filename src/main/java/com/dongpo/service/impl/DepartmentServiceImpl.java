package com.dongpo.service.impl;
import com.dongpo.domain.Department;
import com.dongpo.domain.Position;
import com.dongpo.mapper.DepartmentMapper;
import com.dongpo.service.DepartmentService;
import com.dongpo.vo.PageResultVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {


    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public PageResultVo pageList(Integer page, Integer pageSize,String key) {
        PageResultVo pageResultVo = new PageResultVo();
        Page<Object> page1 = PageHelper.startPage(page, pageSize);
        List<Department> departments = departmentMapper.selectAll(key);
        pageResultVo.setCount(page1.getTotal());
        pageResultVo.setData(departments);
        return pageResultVo;
    }

    @Override
    public void add(Department department) {
        departmentMapper.add(department);
    }

    @Override
    public void delete(Integer depId) {
        departmentMapper.deleteByPrimaryKey(depId);
    }

    /**
     * 删除所有
     * @param depIds
     */
    @Override
    public void deleteAll(Integer[] depIds) {
        for(int i = 0;i<depIds.length;i++){
            departmentMapper.deleteByPrimaryKey(depIds[i]);
        }
    }

    //更新
    @Override
    public void update(Department department) {
        departmentMapper.updateByPrimaryKey(department);
    }

    @Override
    public List<Department> getAll() {

        return departmentMapper.getAll();
    }

}
