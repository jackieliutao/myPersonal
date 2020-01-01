package com.dongpo.service.impl;

import com.dongpo.domain.Workexperience;
import com.dongpo.mapper.WorkexperienceMapper;
import com.dongpo.service.WorkExperienceService;
import com.dongpo.vo.PageResultVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkExperienceServiceImpl implements WorkExperienceService {
    @Autowired
    private WorkexperienceMapper workexperienceMapper;
    @Override
    public PageResultVo pageList(Integer page, Integer limit, String key) {
        Page<Object> objects = PageHelper.startPage(page, limit);
        List<Workexperience> workexperiences = workexperienceMapper.selectAll(key);
        PageResultVo pageResultVo = new PageResultVo();
        pageResultVo.setData(workexperiences);
        pageResultVo.setCount(objects.getTotal());
        return pageResultVo;
    }

    @Override
    public void add(Workexperience workexperience) {
        workexperienceMapper.insert(workexperience);
    }

    @Override
    public void update(Workexperience workexperience) {
        workexperienceMapper.updateByPrimaryKey(workexperience);
    }

    @Override
    public void deleteAll(Integer[] wbIds) {
        for (Integer wbId : wbIds) {
            workexperienceMapper.deleteByPrimaryKey(wbId);
        }
    }

    @Override
    public void delete(Integer wbId) {
        workexperienceMapper.deleteByPrimaryKey(wbId);
    }
}
