package com.jacob.trash.webController;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jacob.trash.dao.UserMapper;
import com.jacob.trash.domain.User;
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
import java.util.UUID;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    Result result;

    @RequestMapping(value = "/list/{page}")
    public String index(Model model,@PathVariable("page") int page){
        Page pages = PageHelper.startPage(page, Constant.PAGE_ROWS);
        List<User> userList = userMapper.selectAll();
        model.addAttribute(userList);
        model.addAttribute("page_num",pages.getPages());
        model.addAttribute("current_num",page);

        return "web/user/user_manage";
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

        return "web/user/user_manage";
    }

    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable("id") String id){
        int num = userMapper.deleteByPrimaryKey(id);
        if(num == 1){
            return result.success("删除成功");
        }else{
            return result.error("删除失败");
        }
    }

    @RequestMapping(value = "/add")
    public String add(){

        return "web/user/user_add.html";
    }

    @RequestMapping(value = "/insert")
    @ResponseBody
    public Result insert(User user){
        user.setId(IDGenerator.getUUID());
        user.setTime(new Date());
        int num = userMapper.insert(user);
        if(num == 1){
            return result.success("添加成功");
        }else{
            return result.error("添加失败");
        }
    }
    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") String id,Model model){
        User user = userMapper.selectByPrimaryKey(id);
        model.addAttribute(user);
        return "web/user/user_edit.html";
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Result update(User user){
        int num = userMapper.updateByPrimaryKey(user);
        if(num == 1){
            return result.success("修改成功");
        }else{
            return result.error("修改失败");
        }
    }

}
