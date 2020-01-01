package com.dongpo.service.impl;

import com.dongpo.domain.Manager;
import com.dongpo.mapper.ManagerMapper;
import com.dongpo.service.ManagerService;
import com.dongpo.vo.PageResultVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public Manager getManagerByName(String username) {
        return managerMapper.getManageByName(username);
    }

    @Override
    public void update(Manager manager) {
        managerMapper.updateByPrimaryKey(manager);
    }

    @Override
    public PageResultVo pageList(Integer page, Integer limit, String key) {
        Page<Object> objects = PageHelper.startPage(page, limit);
        List<Manager> managers = managerMapper.selectAll(key);
        PageResultVo pageResultVo = new PageResultVo();
        pageResultVo.setCount(objects.getTotal());
        pageResultVo.setData(managers);
        return pageResultVo;
    }

    @Override
    public void add(Manager manager) {
        manager.setPassword("123");
        managerMapper.insert(manager);
    }

    @Override
    public void changeStatus(Integer[] ids) {
        for (Integer id : ids) {
            Manager manager = managerMapper.selectByPrimaryKey(id);
            if(manager.getStatus()){
                manager.setStatus(false);
            }else{
                manager.setStatus(true);
            }
            managerMapper.updateByPrimaryKey(manager);
        }
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            managerMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void delete(Integer id) {
        managerMapper.deleteByPrimaryKey(id);
    }
}
