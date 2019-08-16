package com.jacob.trash.service;

import com.jacob.trash.dao.SiteTrashMapper;
import com.jacob.trash.dao.UserSiteMapper;
import com.jacob.trash.domain.SiteTrash;
import com.jacob.trash.domain.UserSite;
import com.jacob.trash.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SiteTrashService {
    @Autowired
    SiteTrashMapper siteTrashMapper;

    @Transactional
    public int insert(String siteId,String[] trashIds){
        int num =  0;
        for(String trashId:trashIds){
            SiteTrash siteTrash = new SiteTrash();
            siteTrash.setId(IDGenerator.getUUID());
            siteTrash.setSiteId(siteId);
            siteTrash.setTrashId(trashId);
            siteTrashMapper.insert(siteTrash);
            num++;
        }
        return num;
    }

    @Transactional
    public int delete(String siteId, String[] trashIds) {
        int num =  0;
        for(String trashId:trashIds){
            siteTrashMapper.deleteByUserIdAndSiteId(siteId,trashId);
            num++;
        }
        return num;
    }
}
