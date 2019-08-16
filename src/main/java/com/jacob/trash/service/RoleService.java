package com.jacob.trash.service;

import com.jacob.trash.dao.RoleMapper;
import com.jacob.trash.domain.Role;
import com.jacob.trash.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("RoleService")
public class RoleService {
    @Autowired
    RedisService redisService;
    @Autowired
    RoleMapper roleMapper;

    public List<Role> getRoles(){
        boolean flag  = redisService.exists("roleList");
        if(!flag){
            System.out.println("未找到redis保存的角色");
            redisService.removePattern("roleList");
            redisService.LSet("roleList",roleMapper.selectAll());
        }
        //返回的是一个List<List<Role>>,好像改不了
        List<Role> roleList = (List<Role>)redisService.LGet("roleList",0L,Long.valueOf(getRoleSize())).get(0);

        return roleList;
    }

    public String getRoleSize(){
        boolean flag = redisService.exists("roleSize");
        if(!flag){
            int num = roleMapper.selectNum();
            redisService.removePattern("roleSize");
            redisService.set("roleSize",String.valueOf(num));
        }
        return redisService.get("roleSize");
    }

    public String getRoleNameById(String roleID){
        List<Role> roleList = getRoles();
        for(Role role:roleList){
            if(role.id.equals(roleID)){
                return role.getName();
            }
        }
        return null;
    }


    public List<Role> selectByName(String name) {
        return roleMapper.selectByName(name);
    }

    public int deleteByPrimaryKey(String id) {
        redisService.remove("roleList");
        return roleMapper.deleteByPrimaryKey(id);
    }

    public int insert(Role role) {
        redisService.remove("roleList");
        return roleMapper.insert(role);
    }

    public Role selectByPrimaryKey(String id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKey(Role role) {
        redisService.remove("roleList");
        return roleMapper.updateByPrimaryKey(role);
    }
}
