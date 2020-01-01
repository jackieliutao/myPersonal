package com.dongpo.service.impl;

import com.dongpo.domain.Professional;
import com.dongpo.mapper.ProfessionalMapper;
import com.dongpo.service.ProfessionalService;
import com.dongpo.vo.PageResultVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProfessionalServiceImpl implements ProfessionalService {
    @Autowired
    private ProfessionalMapper professionalMapper;
    @Override
    public PageResultVo list(Integer page, Integer limit, String key) {
        Page<Object> objects = PageHelper.startPage(page, limit);
        List<Professional> professionals = professionalMapper.selectAll(key);
        PageResultVo pageResultVo = new PageResultVo();
        pageResultVo.setData(professionals);
        pageResultVo.setCount(objects.getTotal());
        return pageResultVo;
    }

    @Override
    public void add(Professional professional) {
        professionalMapper.insert(professional);
    }

    @Override
    public void delete(Integer proId) {
        professionalMapper.deleteByPrimaryKey(proId);
    }

    @Override
    public void update(Professional professional) {
        professionalMapper.updateByPrimaryKey(professional);
    }

    @Override
    public void deleteAll(Integer[] proIds) {
        for (Integer proId : proIds) {
            professionalMapper.deleteByPrimaryKey(proId);
        }
    }

    @Override
    public List<Professional> getProfessionalByDepId(Integer depId) {

        return professionalMapper.getProfessionalByDepId(depId);
    }
}
