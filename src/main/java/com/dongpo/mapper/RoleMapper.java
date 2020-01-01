package com.dongpo.mapper;

import com.dongpo.domain.Role;
import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roId);

    int insert(Role record);

    Role selectByPrimaryKey(Integer roId);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
}