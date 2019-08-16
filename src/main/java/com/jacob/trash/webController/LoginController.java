package com.jacob.trash.webController;

import com.jacob.trash.dao.UserMapper;
import com.jacob.trash.domain.User;
import com.jacob.trash.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller

public class LoginController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    Result result;
    @Autowired
    User user;

    @RequestMapping(value = "/")
    public String index(){
        return "web/login/login";
    }

    @RequestMapping(value = "/test")
    public String test(){
        return "web/common/test";
    }


    @RequestMapping(value = "/login/{account}/{pwd}")
    @ResponseBody
    public Result login(HttpSession session,@PathVariable("account")String account, @PathVariable("pwd")String pwd){
        user = userMapper.selectByAccount(account);
        if(user == null){
            return result.error("用户名不存在");
        }else if(!user.getPwd().equals(pwd)){
            return result.error("密码错误");
        }else{
            session.setAttribute("user",user);
            return result.success("");
        }

    }
}
