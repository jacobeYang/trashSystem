package com.jacob.trash.webController;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jacob.trash.dao.SiteMapper;
import com.jacob.trash.dao.UserMapper;
import com.jacob.trash.dao.UserSiteMapper;
import com.jacob.trash.domain.Site;
import com.jacob.trash.domain.User;
import com.jacob.trash.domain.UserSite;
import com.jacob.trash.service.UserSiteService;
import com.jacob.trash.util.Constant;
import com.jacob.trash.util.IDGenerator;
import com.jacob.trash.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/userToSite")
public class UserToSiteController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserSiteMapper userSiteMapper;
    @Autowired
    SiteMapper siteMapper;
    @Autowired
    Result result;
    @Autowired
    UserSiteService userSiteService;

    @RequestMapping(value = "/list/{page}")
    public String index(Model model,@PathVariable("page") int page){
        Page pages = PageHelper.startPage(page, Constant.PAGE_ROWS);
        List<User> userList = userMapper.selectAll();
        model.addAttribute(userList);
        model.addAttribute("page_num",pages.getPages());
        model.addAttribute("current_num",page);

        return "web/userToSite/userToSite_manage";
    }

    @RequestMapping(value = "/search/{name}/{roleId}/{page}")
    public String search(Model model,@PathVariable("name") String name,@PathVariable("roleId") String roleId,@PathVariable("page") int page){
        Page pages = PageHelper.startPage(page, Constant.PAGE_ROWS);
        List<User> userList = userMapper.selectByNameAndRole( name, roleId);

        model.addAttribute(userList);
        model.addAttribute("page_num",pages.getPages());
        model.addAttribute("current_num",page);
        model.addAttribute(roleId);
        model.addAttribute("name",name.equals("all")?null:name);

        return "web/userToSite/userToSite_manage";
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result delete( String userId,@RequestParam("siteIds[]") String[] siteIds){
        int num =  userSiteService.delete(userId,siteIds);
        if(num == siteIds.length){
            return result.success("删除成功");
        }else{
            return result.error("删除失败");
        }
    }

    @RequestMapping(value = "/add/{userId}/{name}/{typeValue}/{page}")
    public String add(Model model,@PathVariable("userId") String userId,@PathVariable("name") String name,@PathVariable("typeValue") int typeValue,@PathVariable("page") int page){
        List<String> hasSiteIDList = userSiteMapper.selectSiteIdByUserId(userId);
        User user = userMapper.selectByPrimaryKey(userId);
        Page pages = PageHelper.startPage(page, Constant.PAGE_BIG_ROWS);
        List<Site> siteList = siteMapper.selectNotIn(hasSiteIDList,name,typeValue);
        model.addAttribute(siteList);
        model.addAttribute("name",name.equals("all")?null:name);
        model.addAttribute(typeValue);
        model.addAttribute("page_num",pages.getPages());
        model.addAttribute("current_num",page);
        model.addAttribute(userId);
        model.addAttribute("userName",user.getName());
        return "web/userToSite/userToSite_add.html";
    }

    @RequestMapping(value = "/insert",method= RequestMethod.GET)
    @ResponseBody
    public Result insert( String userId,@RequestParam("siteIds[]") String[] siteIds){
        int num =  userSiteService.insert(userId,siteIds);
        if(num == siteIds.length){
            return result.success("添加成功");
        }else{
            return result.error("添加失败");
        }

    }
    @RequestMapping(value = "/edit/{userId}/{name}/{typeValue}/{page}")
    public String edit(Model model,@PathVariable("userId") String userId,@PathVariable("name") String name,@PathVariable("typeValue") int typeValue,@PathVariable("page") int page){
        List<String> hasSiteIDList = userSiteMapper.selectSiteIdByUserId(userId);
        User user = userMapper.selectByPrimaryKey(userId);
        Page pages = PageHelper.startPage(page, Constant.PAGE_BIG_ROWS);
        List<Site> siteList = siteMapper.selectIn(hasSiteIDList,name,typeValue);
        model.addAttribute(siteList);
        model.addAttribute("name",name.equals("all")?null:name);
        model.addAttribute(typeValue);
        model.addAttribute("page_num",pages.getPages());
        model.addAttribute("current_num",page);
        model.addAttribute(userId);
        model.addAttribute("userName",user.getName());
        return "web/userToSite/userToSite_edit.html";
    }



}
