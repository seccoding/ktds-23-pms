package com.ktdsuniversity.edu.pms.menu.dao;

import com.ktdsuniversity.edu.pms.menu.vo.MenuVO;

import java.util.List;

public interface MenuDao {

    public static final String NAME_SPACE = "com.ktdsuniversity.edu.pms.menu.dao.MenuDao";

    List<MenuVO> selectAllMenuList();

    List<MenuVO> selectAllHierarchicalMenuList();

    int insertNewMenu(MenuVO menuVO);

    int updateMenu(MenuVO menuVO);

    int deleteMenu(String id);
}
