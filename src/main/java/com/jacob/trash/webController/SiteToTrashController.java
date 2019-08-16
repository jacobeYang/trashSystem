package com.jacob.trash.webController;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jacob.trash.dao.SiteMapper;
import com.jacob.trash.dao.SiteTrashMapper;
import com.jacob.trash.dao.TrashMapper;
import com.jacob.trash.domain.Site;
import com.jacob.trash.domain.SiteTrash;
import com.jacob.trash.domain.Trash;
import com.jacob.trash.service.SiteTrashService;
import com.jacob.trash.util.Constant;
import com.jacob.trash.util.IDGenerator;
import com.jacob.trash.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/siteToTrash")
public class SiteToTrashController {
    @Autowired
    SiteMapper siteMapper;
    @Autowired
    TrashMapper trashMapper;
    @Autowired
    Result result;
    @Autowired
    SiteTrashMapper siteTrashMapper;
    @Autowired
    SiteTrashService siteTrashService;

    @RequestMapping(value = "/list/{page}")
    public String index(Model model,@PathVariable("page") int page){
        Page pages = PageHelper.startPage(page, Constant.PAGE_ROWS);
        List<Site> siteList = siteMapper.selectAll();
        model.addAttribute(siteList);
        model.addAttribute("page_num",pages.getPages());
        model.addAttribute("current_num",page);

        return "web/siteToTrash/siteToTrash_manage";
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

        return "web/siteToTrash/siteToTrash_manage";
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result delete(String siteId,@RequestParam("trashIds[]") String[] trashIds){
        int num = siteTrashService.delete(siteId,trashIds);
        if(num == trashIds.length){
            return result.success("删除成功");
        }else{
            return result.error("删除失败");
        }
    }

    @RequestMapping(value = "/add/{siteId}/{typeValue}/{page}")
    public String add(Model model,@PathVariable("siteId") String siteId,@PathVariable("typeValue") int typeValue,@PathVariable("page") int page){
        List<String> hasTrashIdList = siteTrashMapper.selectTrashIdBySiteId(siteId);
        Site site = siteMapper.selectByPrimaryKey(siteId);
        Page pages = PageHelper.startPage(page, Constant.PAGE_BIG_ROWS);
        List<Trash> trashList = trashMapper.selectNoIn(hasTrashIdList,typeValue); //trashMapper.selectNoIn(hasTrashIdList,typeValue);
        model.addAttribute(trashList);
        model.addAttribute("page_num",pages.getPages());
        model.addAttribute("current_num",page);
        model.addAttribute(typeValue);
        model.addAttribute("siteName",site.getName());
        model.addAttribute("siteId",siteId);

        return "web/siteToTrash/siteToTrash_add.html";
    }

    @RequestMapping(value = "/insert")
    @ResponseBody
    public Result insert(String siteId,@RequestParam("trashIds[]") String[] trashIds){
        int num = siteTrashService.insert(siteId,trashIds);
        if(num == trashIds.length){
            return result.success("添加成功");
        }else{
            return result.error("添加失败");
        }
    }
    @RequestMapping(value = "/edit/{siteId}/{typeValue}/{page}")
    public String edit(Model model,@PathVariable("siteId") String siteId,@PathVariable("typeValue") int typeValue,@PathVariable("page") int page){
        List<String> hasTrashIdList = siteTrashMapper.selectTrashIdBySiteId(siteId);
        Site site = siteMapper.selectByPrimaryKey(siteId);
        Page pages = PageHelper.startPage(page, Constant.PAGE_BIG_ROWS);
        List<Trash> trashList = trashMapper.selectIn(hasTrashIdList,typeValue); //trashMapper.selectNoIn(hasTrashIdList,typeValue);
        model.addAttribute(trashList);
        model.addAttribute("page_num",pages.getPages());
        model.addAttribute("current_num",page);
        model.addAttribute(typeValue);
        model.addAttribute("siteName",site.getName());
        model.addAttribute("siteId",siteId);

        return "web/siteToTrash/siteToTrash_edit.html";
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Result update(Site siteToTrash){
        int num = siteMapper.updateByPrimaryKey(siteToTrash);
        if(num == 1){
            return result.success("修改成功");
        }else{
            return result.error("修改失败");
        }
    }

}
