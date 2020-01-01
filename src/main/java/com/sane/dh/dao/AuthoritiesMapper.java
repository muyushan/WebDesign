package com.sane.dh.dao;

import com.sane.dh.model.Authorities;
import com.sane.dh.model.AuthoritiesCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthoritiesMapper {
    int countByExample(AuthoritiesCriteria example);

    int deleteByExample(AuthoritiesCriteria example);

    int deleteByPrimaryKey(String username);

    int insert(Authorities record);

    int insertSelective(Authorities record);

    List<Authorities> selectByExample(AuthoritiesCriteria example);

    Authorities selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") Authorities record, @Param("example") AuthoritiesCriteria example);

    int updateByExample(@Param("record") Authorities record, @Param("example") AuthoritiesCriteria example);

    int updateByPrimaryKeySelective(Authorities record);

    int updateByPrimaryKey(Authorities record);
}