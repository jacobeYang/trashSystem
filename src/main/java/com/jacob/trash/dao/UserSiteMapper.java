package com.jacob.trash.dao;

import com.jacob.trash.domain.UserSite;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSiteMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserSite record);

    int insertSelective(UserSite record);

    UserSite selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserSite record);

    int updateByPrimaryKey(UserSite record);

    List<String> selectSiteIdByUserId(String id);


    void deleteByUserIdAndSiteId(String userId, String siteId);
}