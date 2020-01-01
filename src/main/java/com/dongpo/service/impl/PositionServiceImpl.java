package com.dongpo.service.impl;

import com.dongpo.domain.Position;
import com.dongpo.mapper.EmployeeMapper;
import com.dongpo.mapper.PositionMapper;
import com.dongpo.service.PositionService;
import com.dongpo.vo.PageResultVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public PageResultVo pageList(Integer page, Integer pageSize,String key) {
        Page<Object> objects = PageHelper.startPage(page, pageSize);
        List<Position> positions = positionMapper.selectAll(key);
        PageResultVo pageResultVo = new PageResultVo();
        pageResultVo.setData(positions);
        pageResultVo.setCount(objects.getTotal());
        return pageResultVo;
    }

    //删除
    @Override
    public void delete(Integer pid) {
        //先删除联系
        employeeMapper.updateRelation(pid);
        positionMapper.deleteByPrimaryKey(pid);
    }

    //删除所有
    @Override
    public void deleteAll(Integer[] pids) {
        for (Integer pid : pids) {
            employeeMapper.updateRelation(pid);
            positionMapper.deleteByPrimaryKey(pid);
        }
    }

    //添加
    @Override
    public void addPosition(Position position) {
        positionMapper.add(position);
    }

    //更新
    @Override
    public void update(Position position) {
        positionMapper.updateByPrimaryKey(position);
    }

    @Override
    public List<Position> getAll() {
        return positionMapper.getAll();
    }

    @Override
    public List<Position> getPositionByDepId(Integer depId) {
        return positionMapper.getPositionByDepId(depId);
    }
}
