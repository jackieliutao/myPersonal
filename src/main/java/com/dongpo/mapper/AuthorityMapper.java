package com.dongpo.mapper;

import com.dongpo.domain.Authority;
import java.util.List;

public interface AuthorityMapper {
    int deleteByPrimaryKey(Integer auId);

    int insert(Authority record);

    Authority selectByPrimaryKey(Integer auId);

    List<Authority> selectAll();

    int updateByPrimaryKey(Authority record);
}