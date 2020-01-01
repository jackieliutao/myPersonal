package com.dongpo.service;

import com.dongpo.domain.Reward;
import com.dongpo.vo.PageResultVo;

public interface RewardService {
    PageResultVo pageList(Integer page, Integer limit, String key);

    void update(Reward reward);

    void delete(Integer reId);

    void deleteAll(Integer[] reIds);

    void add(Reward reward);
}
