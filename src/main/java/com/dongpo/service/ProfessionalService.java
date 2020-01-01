package com.dongpo.service;

import com.dongpo.domain.Professional;
import com.dongpo.vo.PageResultVo;

import java.util.List;

public interface ProfessionalService {
    PageResultVo list(Integer page, Integer limit, String key);

    void add(Professional professional);

    void delete(Integer proId);

    void update(Professional professional);

    void deleteAll(Integer[] proIds);

    List<Professional> getProfessionalByDepId(Integer depId);
}
