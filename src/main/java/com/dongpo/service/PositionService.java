package com.dongpo.service;

import com.dongpo.domain.Position;
import com.dongpo.vo.PageResultVo;

import java.util.List;

public interface PositionService {
    PageResultVo pageList(Integer page, Integer pageSize,String key);

    void delete(Integer pid);

    void deleteAll(Integer[] pids);

    void addPosition(Position position);

    void update(Position position);

    List<Position> getAll();

    List<Position> getPositionByDepId(Integer depId);
}
