package com.ktdsuniversity.edu.pms.menu.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ktdsuniversity.edu.pms.menu.service.MenuService;
import com.ktdsuniversity.edu.pms.menu.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ResponseBody
    @GetMapping(value = "/menu", produces = "text/plain;charset=UTF-8")
    public String viewMenuListPage(Model model) {

        List<MenuVO> menuList = menuService.getAllMenuList();

        Gson gson = new GsonBuilder().create();

        return gson.toJson(menuList);
    }

}
