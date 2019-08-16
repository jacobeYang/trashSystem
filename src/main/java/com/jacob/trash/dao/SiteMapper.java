package com.jacob.trash.dao;

import com.jacob.trash.domain.Site;
import com.jacob.trash.domain.Trash;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteMapper {
    int deleteByPrimaryKey(String id);

    int insert(Site record);

    int insertSelective(Site record);

    Site selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKey(Site record);

    List<Site> selectAll();

    List<Site> selectByNameAndType(String name, int type);

    List<Site> selectNotIn(List<String> hasSiteIDList,String name,int typeValue);

    List<Site> selectIn(List<String> hasSiteIDList, String name, int typeValue);


}