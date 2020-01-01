package com.dongpo.mapper;

import com.dongpo.domain.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Manager record);

    Manager selectByPrimaryKey(Integer id);

    List<Manager> selectAll(@Param("key") String key);

    int updateByPrimaryKey(Manager record);

    Manager getManageByName(String username);
}