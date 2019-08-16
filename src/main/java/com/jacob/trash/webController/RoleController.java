package com.jacob.trash.webController;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jacob.trash.dao.RoleMapper;
import com.jacob.trash.dao.RoleMapper;
import com.jacob.trash.domain.Role;
import com.jacob.trash.domain.Role;
import com.jacob.trash.service.RoleService;
import com.jacob.trash.util.Constant;
import com.jacob.trash.util.IDGenerator;
import com.jacob.trash.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    Result result;

    @RequestMapping(value = "/list/{page}")
    public String index(Model model,@PathVariable("page") int page){
        Page pages = PageHelper.startPage(page, Constant.PAGE_ROWS);
        List<Role> roleList = roleService.getRoles();
        model.addAttribute(roleList);
        model.addAttribute("page_num",pages.getPages());
        model.addAttribute("current_num",page);

        return "web/role/role_manage";
    }

    @RequestMapping(value = "/search/{name}/{page}")
    public String search(Model model,@PathVariable("name") String name,@PathVariable("page") int page){
        Page pages = PageHelper.startPage(page, Constant.PAGE_ROWS);
        List<Role> roleList = roleService.selectByName( name);

        model.addAttribute("roleList",roleList);
        model.addAttribute("page_num",pages.getPages());
        model.addAttribute("current_num",page);
        model.addAttribute("name",name.equals("all")?null:name);

        return "web/role/role_manage";
    }

    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable("id") String id){
        int num = roleService.deleteByPrimaryKey(id);
        if(num == 1){
            return result.success("删除成功");
         }else{
            return result.error("删除失败");
        }
    }

    @RequestMapping(value = "/add")
    public String add(){

        return "web/role/role_add.html";
    }

    @RequestMapping(value = "/insert")
    @ResponseBody
    public Result insert(Role role){
        role.setId(IDGenerator.getUUID());
        int num = roleService.insert(role);
        if(num == 1){
            return result.success("添加成功");
        }else{
            return result.error("添加失败");
        }
    }
    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") String id,Model model){
        Role role = roleService.selectByPrimaryKey(id);
        model.addAttribute(role);
        return "web/role/role_edit.html";
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Result update(Role role){
        int num = roleService.updateByPrimaryKey(role);
        if(num == 1){
            return result.success("修改成功");
        }else{
            return result.error("修改失败");
        }
    }

}
