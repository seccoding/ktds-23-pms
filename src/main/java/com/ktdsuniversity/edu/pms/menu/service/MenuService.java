package com.ktdsuniversity.edu.pms.menu.service;

import com.ktdsuniversity.edu.pms.menu.vo.MenuVO;

import java.util.List;

public interface MenuService {
    List<MenuVO> getAllMenuList(boolean isAdminUser);

    List<MenuVO> getBussinessSupportMenuList();

    List<MenuVO> getAllHierarchicalMenuList();

    List<MenuVO> getAllFlatMenuList();

    boolean saveNewMenu(MenuVO menuVO);

    boolean updateMenu(MenuVO menuVO);

    boolean deleteMenu(String id);
}
