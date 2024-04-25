package com.ktdsuniversity.edu.pms.menu.service;

import com.ktdsuniversity.edu.pms.menu.dao.MenuDao;
import com.ktdsuniversity.edu.pms.menu.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    private static List<MenuVO> HIERARCHICAL_MENU_LIST;
    private static List<MenuVO> FLAT_MENU_LIST;

    public static boolean NEED_RELOAD;
    private static Object LOCK;

    public static void needReload(boolean needReload) {
        NEED_RELOAD = needReload;
    }

    static {
        NEED_RELOAD = false;

        if (FLAT_MENU_LIST == null || HIERARCHICAL_MENU_LIST == null) {
            NEED_RELOAD = true;
        }

        LOCK = new Object();
    }

    public static List<MenuVO> filterMenus(List<MenuVO> menus) {
        if (menus == null) return null;

        return menus.stream()
                .filter(menu -> (menu.getRole() == null || menu.getRole().equals("USER")) && !menu.getId().equals("1-3"))
                .peek(menu -> {
                    // 자식 메뉴도 같은 필터를 적용
                    List<MenuVO> filteredChildren = filterMenus(menu.getChildren());
                    if (filteredChildren != null && !filteredChildren.isEmpty()) {
                        menu.setChildren(filteredChildren);
                    }
                })
                .collect(Collectors.toList());
    }

    public static List<MenuVO> filterBusinessSupportMenus(List<MenuVO> menus) {
        if (menus == null) return null;

        return menus.stream()
                .filter(menu -> (menu.getRole() == null || menu.getRole().equals("USER")))
                .peek(menu -> {
                    // 자식 메뉴도 같은 필터를 적용
                    List<MenuVO> filteredChildren = filterBusinessSupportMenus(menu.getChildren());
                    if (filteredChildren != null && !filteredChildren.isEmpty()) {
                        menu.setChildren(filteredChildren);
                    }
                })
                .collect(Collectors.toList());
    }

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<MenuVO> getAllMenuList(boolean isAdminUser) {
        synchronized (LOCK) {
            if (NEED_RELOAD) {
                HIERARCHICAL_MENU_LIST = menuDao.selectAllHierarchicalMenuList();
            }
        }

        if (isAdminUser) {
            return HIERARCHICAL_MENU_LIST;
        } else {
            return filterMenus(HIERARCHICAL_MENU_LIST);
        }
    }

    @Override
    public List<MenuVO> getBussinessSupportMenuList() {
        synchronized (LOCK) {
            if (NEED_RELOAD) {
                HIERARCHICAL_MENU_LIST = menuDao.selectAllHierarchicalMenuList();
            }
        }

        return filterBusinessSupportMenus(HIERARCHICAL_MENU_LIST);
    }

    @Override
    public List<MenuVO> getAllHierarchicalMenuList() {
        synchronized (LOCK) {
            if (NEED_RELOAD) {
                HIERARCHICAL_MENU_LIST = menuDao.selectAllHierarchicalMenuList();
            }
        }

        return HIERARCHICAL_MENU_LIST;
    }

    @Override
    public List<MenuVO> getAllFlatMenuList() {
        synchronized (LOCK) {
            if (NEED_RELOAD) {
                FLAT_MENU_LIST = menuDao.selectAllMenuList();
            }
        }

        return FLAT_MENU_LIST;
    }

    @Transactional
    @Override
    public boolean saveNewMenu(MenuVO menuVO) {
        int insertedCount = menuDao.insertNewMenu(menuVO);

        needReload(insertedCount > 0);
        return insertedCount > 0;
    }

    @Transactional
    @Override
    public boolean updateMenu(MenuVO menuVO) {
        int insertedCount = menuDao.updateMenu(menuVO);

        needReload(insertedCount > 0);
        return insertedCount > 0;
    }

    @Transactional
    @Override
    public boolean deleteMenu(String id) {
        int insertedCount = menuDao.deleteMenu(id);

        needReload(insertedCount > 0);
        return insertedCount > 0;
    }
}
