package com.jacob.trash.webController;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jacob.trash.dao.SiteMapper;
import com.jacob.trash.domain.Site;
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
@RequestMapping(value = "/site")
public class SiteController {
    @Autowired
    SiteMapper siteMapper;
    @Autowired
    Result result;

    @RequestMapping(value = "/list/{page}")
    public String index(Model model,@PathVariable("page") int page){
        Page pages = PageHelper.startPage(page, Constant.PAGE_ROWS);
        List<Site> siteList = siteMapper.selectAll();
        model.addAttribute(siteList);
        model.addAttribute("page_num",pages.getPages());
        model.addAttribute("current_num",page);

        return "web/site/site_manage";
    }

    @RequestMapping(value = "/search/{name}/{typeValue}/{page}")
    public String search(Model model,@PathVariable("name") String name,@PathVariable("typeValue") int typeValue,@PathVariable("page") int page){
        Page pages = PageHelper.startPage(page, Constant.PAGE_ROWS);
        List<Site> siteList = siteMapper.selectByNameAndType( name, typeValue);

        model.addAttribute(siteList);
        model.addAttribute("page_num",pages.getPages());
        model.addAttribute("current_num",page);
        model.addAttribute(typeValue);
        model.addAttribute("name",name.equals("all")?null:name);

        return "web/site/site_manage";
    }

    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable("id") String id){
        int num = siteMapper.deleteByPrimaryKey(id);
        if(num == 1){
            return result.success("删除成功");
        }else{
            return result.error("删除失败");
        }
    }

    @RequestMapping(value = "/add")
    public String add(){

        return "web/site/site_add.html";
    }

    @RequestMapping(value = "/insert")
    @ResponseBody
    public Result insert(Site site){
        site.setId(IDGenerator.getUUID());
        site.setTrashNumber(0);
        int num = siteMapper.insert(site);
        if(num == 1){
            return result.success("添加成功");
        }else{
            return result.error("添加失败");
        }
    }
    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") String id,Model model){
        Site site = siteMapper.selectByPrimaryKey(id);
        model.addAttribute(site);
        return "web/site/site_edit.html";
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Result update(Site site){
        int num = siteMapper.updateByPrimaryKey(site);
        if(num == 1){
            return result.success("修改成功");
        }else{
            return result.error("修改失败");
        }
    }

}
