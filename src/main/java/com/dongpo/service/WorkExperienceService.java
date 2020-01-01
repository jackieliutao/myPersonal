package com.dongpo.service;

import com.dongpo.domain.Workexperience;
import com.dongpo.vo.PageResultVo;

public interface WorkExperienceService {
    PageResultVo pageList(Integer page, Integer limit, String key);

    void add(Workexperience workexperience);

    void update(Workexperience workexperience);

    void deleteAll(Integer[] wbIds);

    void delete(Integer wbId);
}
