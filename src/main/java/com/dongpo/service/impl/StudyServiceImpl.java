package com.dongpo.service.impl;

import com.dongpo.domain.Study;
import com.dongpo.mapper.StudyMapper;
import com.dongpo.service.StudyService;
import com.dongpo.vo.PageResultVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudyServiceImpl implements StudyService {
    @Autowired
    private StudyMapper studyMapper;

    @Override
    public PageResultVo pageList(Integer page, Integer limit, String key) {
        Page<Object> objects = PageHelper.startPage(page, limit);
        List<Study> studies = studyMapper.selectAll(key);
        PageResultVo pageResultVo = new PageResultVo();
        pageResultVo.setCount(objects.getTotal());
        pageResultVo.setData(studies);
        return pageResultVo;
    }

    @Override
    public void add(Study study) {
        studyMapper.insert(study);
    }

    @Override
    public void update(Study study) {
        studyMapper.updateByPrimaryKey(study);
    }

    @Override
    public void delete(Integer sId) {
        studyMapper.deleteByPrimaryKey(sId);
    }

    @Override
    public void deleteAll(Integer[] sIds) {
        for (Integer sId : sIds) {
            studyMapper.deleteByPrimaryKey(sId);
        }
    }
}
