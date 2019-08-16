package com.jacob.trash.service;

import com.jacob.trash.dao.UserSiteMapper;
import com.jacob.trash.domain.UserSite;
import com.jacob.trash.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSiteService {
    @Autowired
    UserSiteMapper userSiteMapper;

    @Transactional
    public int insert(String userId,String[] siteIds){
        int num =  0;
        for(String siteId:siteIds){
            UserSite userSite = new UserSite();
            userSite.setId(IDGenerator.getUUID());
            userSite.setUserId(userId);
            userSite.setSiteId(siteId);
            userSiteMapper.insert(userSite);
            num++;
        }
        return num;
    }

    @Transactional
    public int delete(String userId, String[] siteIds) {
        int num =  0;
        for(String siteId:siteIds){
            userSiteMapper.deleteByUserIdAndSiteId(userId,siteId);
            num++;
        }
        return num;
    }
}
