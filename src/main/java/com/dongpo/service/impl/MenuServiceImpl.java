package com.dongpo.service.impl;

import com.dongpo.domain.Menu;
import com.dongpo.mapper.MenuMapper;
import com.dongpo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findAll() {
        List<Menu> menus = menuMapper.selectAll();
        if(!"admin".equals(SecurityContextHolder.getContext().getAuthentication().getName())){
            Iterator<Menu> iterator = menus.iterator();
            while(iterator.hasNext()){
                Menu menu = iterator.next();
                if(menu.getTitle().contains("管理员")){
                    iterator.remove();
                }
            }
        }
        return menus;
    }
}
