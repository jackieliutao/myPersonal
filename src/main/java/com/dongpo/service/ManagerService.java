package com.dongpo.service;

import com.dongpo.domain.Manager;
import com.dongpo.vo.PageResultVo;

public interface ManagerService {
    Manager getManagerByName(String username);

    void update(Manager manager);

    PageResultVo pageList(Integer page, Integer limit, String key);

    void add(Manager manager);

    void changeStatus(Integer[] ids);

    void deleteAll(Integer[] ids);

    void delete(Integer id);
}
