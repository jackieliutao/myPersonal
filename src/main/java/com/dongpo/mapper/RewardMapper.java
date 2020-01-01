package com.dongpo.mapper;

import com.dongpo.domain.Reward;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RewardMapper {
    int deleteByPrimaryKey(Integer reId);

    int insert(Reward record);

    Reward selectByPrimaryKey(Integer reId);

    List<Reward> selectAll(@Param("key") String key);

    int updateByPrimaryKey(Reward record);
}