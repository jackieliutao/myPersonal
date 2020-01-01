package com.dongpo.service;

import com.dongpo.domain.Study;
import com.dongpo.vo.PageResultVo;

public interface StudyService {
    PageResultVo pageList(Integer page, Integer limit, String key);

    void add(Study study);

    void update(Study study);

    void delete(Integer sId);

    void deleteAll(Integer[] sIds);
}
