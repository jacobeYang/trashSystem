package com.jacob.trash.webController;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jacob.trash.dao.TrashMapper;
import com.jacob.trash.dao.UserMapper;
import com.jacob.trash.domain.Trash;
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

@Controller
@RequestMapping(value = "/trash")
public class TrashController {
    @Autowired
    TrashMapper trashMapper;
    @Autowired
    Result result;

    @RequestMapping(value = "/list/{page}")
    public String index(Model model,@PathVariable("page") int page){
        Page pages = PageHelper.startPage(page, Constant.PAGE_ROWS);
        List<Trash> trashList = trashMapper.selectAll();
        model.addAttribute(trashList);
        model.addAttribute("page_num",pages.getPages());
        model.addAttribute("current_num",page);

        return "web/trash/trash_manage";
    }

    @RequestMapping(value = "/search/{typeValue}/{page}")
    public String search(Model model,@PathVariable("typeValue") int typeValue,@PathVariable("page") int page){
        Page pages = PageHelper.startPage(page, Constant.PAGE_ROWS);
        List<Trash> trashList = trashMapper.selectByType( typeValue);

        model.addAttribute(trashList);
        int page_num = pages.getPages();
        model.addAttribute("page_num",page_num);
        model.addAttribute("current_num",page);
        model.addAttribute(typeValue);

        return "web/trash/trash_manage";
    }

    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable("id") String id){
        int num = trashMapper.deleteByPrimaryKey(id);
        if(num == 1){
            return result.success("删除成功");
        }else{
            return result.error("删除失败");
        }
    }

    @RequestMapping(value = "/add")
    public String add(){
        return "web/trash/trash_add.html";
    }

    @RequestMapping(value = "/insert")
    @ResponseBody
    public Result insert(Trash trash){
        trash.setId(IDGenerator.getUUID());
        trash.setTime(new Date());
        trash.setCapacity(0);
        int num = trashMapper.insert(trash);
        if(num == 1){
            return result.success("添加成功");
        }else{
            return result.error("添加失败");
        }
    }
    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") String id,Model model){
        Trash trash = trashMapper.selectByPrimaryKey(id);
        model.addAttribute(trash);
        return "web/trash/trash_edit.html";
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Result update(Trash trash){
        int num = trashMapper.updateByPrimaryKey(trash);
        if(num == 1){
            return result.success("修改成功");
        }else{
            return result.error("修改失败");
        }
    }

}
