package com.jacob.trash.dao;

import com.jacob.trash.domain.SiteFullMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteFullMessageMapper {
    int deleteByPrimaryKey(String id);

    int insert(SiteFullMessage record);

    int insertSelective(SiteFullMessage record);

    SiteFullMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SiteFullMessage record);

    int updateByPrimaryKey(SiteFullMessage record);
}