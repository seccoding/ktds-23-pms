package com.ktdsuniversity.edu.pms.menu.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.menu.service.MenuService;
import com.ktdsuniversity.edu.pms.menu.vo.MenuVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ResponseBody
    @GetMapping(value = "/menu", produces = "application/json;charset=UTF-8")
    public String viewMenuList(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

        Gson gson = new GsonBuilder().create();

        // 경영지원부서인 경우
        if (employeeVO.getAdmnCode().equals("302") && employeeVO.getWorkSts().equals("201") && employeeVO.getDeptId().equals("DEPT_230101_000010")) {
            return gson.toJson(menuService.getBussinessSupportMenuList());
        }

        return gson.toJson(menuService.getAllMenuList(employeeVO.getAdmnCode().equals("301")));
    }

    @GetMapping("/menu/manage")
    public String viewMenuManagementPage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, Model model) {

        if (!employeeVO.getAdmnCode().equals("301")) {
            throw new PageNotFoundException();
        }

        List<MenuVO> menuList = menuService.getAllHierarchicalMenuList();

        model.addAttribute("menuList", menuList.stream()
                .filter(menu -> menu.getParent() == null).toList());

        return "menu/menulist";
    }

    @ResponseBody
    @GetMapping("/ajax/menu/{pid}")
    public AjaxResponse getSubMenuByPID(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, @PathVariable String pid) {

        if (!employeeVO.getAdmnCode().equals("301")) {
            throw new PageNotFoundException();
        }

        List<MenuVO> menuList = menuService.getAllFlatMenuList();

        return new AjaxResponse().append("menuList", menuList.stream()
                .filter(menu -> menu.getParent() != null)
                .filter(menu -> menu.getParent().equals(pid))
                .toList());
    }

    @ResponseBody
    @GetMapping("/ajax/menu/reload")
    public AjaxResponse getMenu(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

        if (!employeeVO.getAdmnCode().equals("301")) {
            throw new PageNotFoundException();
        }

        List<MenuVO> menuList = menuService.getAllFlatMenuList();

        return new AjaxResponse().append("menuList", menuList.stream()
                .filter(menu -> menu.getParent() == null)
                .toList());

    }

    @ResponseBody
    @PostMapping("/ajax/menu/new")
    public AjaxResponse saveNewMenu(
            @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
            MenuVO menuVO) {

        if (!employeeVO.getAdmnCode().equals("301")) {
            throw new PageNotFoundException();
        }

        boolean isSuccess = menuService.saveNewMenu(menuVO);

        return new AjaxResponse().append("result", isSuccess);
    }

    @ResponseBody
    @PostMapping("/ajax/menu/update")
    public AjaxResponse updateMenu(
            @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
            MenuVO menuVO) {

        if (!employeeVO.getAdmnCode().equals("301")) {
            throw new PageNotFoundException();
        }

        boolean isSuccess = menuService.updateMenu(menuVO);

        return new AjaxResponse().append("result", isSuccess);
    }

    @ResponseBody
    @GetMapping("/ajax/menu/delete/{id}")
    public AjaxResponse deleteMenu(
            @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
            @PathVariable String id) {

        if (!employeeVO.getAdmnCode().equals("301")) {
            throw new PageNotFoundException();
        }

        boolean isSuccess = menuService.deleteMenu(id);

        return new AjaxResponse().append("result", isSuccess);
    }
}
