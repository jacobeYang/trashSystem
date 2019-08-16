package com.jacob.trash.webController;


import com.jacob.trash.domain.User;
import com.jacob.trash.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/home")
    public String index(Model model, HttpSession session){
        User user = (User)session.getAttribute("user");//不起作用
        if(user == null){
            return "web/login/login";
        }

        String roleName = roleService.getRoleNameById(user.getRoleId());
        model.addAttribute(user);
        model.addAttribute("roleName",roleName);
        return "web/main";
    }
}
