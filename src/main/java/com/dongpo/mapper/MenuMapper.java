package com.dongpo.mapper;

import com.dongpo.domain.Menu;
import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);
    List<Menu> selectByParentId(Integer id);
}