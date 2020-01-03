package com.sane.dh.dao;

import com.sane.dh.model.PersistentLogins;
import com.sane.dh.model.PersistentLoginsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersistentLoginsMapper {
    int countByExample(PersistentLoginsCriteria example);

    int deleteByExample(PersistentLoginsCriteria example);

    int deleteByPrimaryKey(String series);

    int insert(PersistentLogins record);

    int insertSelective(PersistentLogins record);

    List<PersistentLogins> selectByExample(PersistentLoginsCriteria example);

    PersistentLogins selectByPrimaryKey(String series);

    int updateByExampleSelective(@Param("record") PersistentLogins record, @Param("example") PersistentLoginsCriteria example);

    int updateByExample(@Param("record") PersistentLogins record, @Param("example") PersistentLoginsCriteria example);

    int updateByPrimaryKeySelective(PersistentLogins record);

    int updateByPrimaryKey(PersistentLogins record);
}