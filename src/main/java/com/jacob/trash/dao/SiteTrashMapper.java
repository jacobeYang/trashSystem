package com.jacob.trash.dao;

import com.jacob.trash.domain.SiteTrash;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteTrashMapper {
    int deleteByPrimaryKey(String id);

    int insert(SiteTrash record);

    int insertSelective(SiteTrash record);

    SiteTrash selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SiteTrash record);

    int updateByPrimaryKey(SiteTrash record);

    List<String> selectTrashIdBySiteId(String siteId);

    void deleteByUserIdAndSiteId(String siteId, String trashId);
}