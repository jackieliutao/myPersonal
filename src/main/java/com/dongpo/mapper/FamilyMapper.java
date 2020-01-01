package com.dongpo.mapper;

import com.dongpo.domain.Family;
import java.util.List;

public interface FamilyMapper {
    int deleteByPrimaryKey(Integer faId);

    int insert(Family record);

    Family selectByPrimaryKey(Integer faId);

    List<Family> selectAll();

    int updateByPrimaryKey(Family record);

    Family getFamilyByEmpId(Integer empId);

    void deleteByEmpId(Integer empId);
}