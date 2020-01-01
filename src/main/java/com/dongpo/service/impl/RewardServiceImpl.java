package com.dongpo.service.impl;

import com.dongpo.domain.Reward;
import com.dongpo.mapper.RewardMapper;
import com.dongpo.service.RewardService;
import com.dongpo.vo.PageResultVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RewardServiceImpl implements RewardService {
    @Autowired
    private RewardMapper rewardMapper;

    @Override
    public PageResultVo pageList(Integer page, Integer limit, String key) {
        Page<Object> objects = PageHelper.startPage(page, limit);
        List<Reward> rewards = rewardMapper.selectAll(key);
        PageResultVo pageResultVo = new PageResultVo();
        pageResultVo.setData(rewards);
        pageResultVo.setCount(objects.getTotal());
        return pageResultVo;
    }

    @Override
    public void update(Reward reward) {
        rewardMapper.updateByPrimaryKey(reward);
    }

    @Override
    public void delete(Integer reId) {
        rewardMapper.deleteByPrimaryKey(reId);
    }

    @Override
    public void deleteAll(Integer[] reIds) {
        for (Integer reId : reIds) {
            rewardMapper.deleteByPrimaryKey(reId);
        }
    }

    @Override
    public void add(Reward reward) {
        rewardMapper.insert(reward);
    }
}
