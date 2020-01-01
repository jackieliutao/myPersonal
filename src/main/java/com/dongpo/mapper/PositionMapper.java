package com.dongpo.mapper;

import com.dongpo.domain.Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer poId);

    int insert(Position record);

    Position selectByPrimaryKey(Integer poId);

    List<Position> selectAll(@Param("key") String key);

    int updateByPrimaryKey(Position record);

    void add(Position position);

    List<Position> getAll();

    List<Position> getPositionByDepId(Integer depId);
}