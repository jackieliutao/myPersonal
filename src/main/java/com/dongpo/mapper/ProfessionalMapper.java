package com.dongpo.mapper;
import com.dongpo.domain.Professional;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface ProfessionalMapper {
    int deleteByPrimaryKey(Integer proId);

    int insert(Professional record);

    Professional selectByPrimaryKey(Integer proId);

    List<Professional> selectAll(@Param("key") String key);

    int updateByPrimaryKey(Professional record);

    List<Professional> getProfessionalByDepId(Integer depId);
}