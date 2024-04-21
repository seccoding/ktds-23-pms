package com.ktdsuniversity.edu.pms.menu.service;

import com.ktdsuniversity.edu.pms.menu.dao.MenuDao;
import com.ktdsuniversity.edu.pms.menu.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    private static List<MenuVO> MENU_LIST;
    public static boolean NEED_RELOAD;
    private static Object LOCK;

    public static void needReload(boolean needReload) {
        NEED_RELOAD = needReload;
    }

    static {
        NEED_RELOAD = false;
        if (MENU_LIST == null) {
            NEED_RELOAD = true;
        }

        LOCK = new Object();
    }

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<MenuVO> getAllMenuList() {
        synchronized (LOCK) {
            if (NEED_RELOAD) {
                List<MenuVO> flatMenuList = menuDao.selectAllMenuList();

                Map<String, MenuVO> menuMap = flatMenuList.stream()
                        .collect(Collectors.toMap(MenuVO::getId, Function.identity()));

                menuMap.values().forEach(menu -> menu.setChildren(new ArrayList<>()));

                List<MenuVO> hierarchicalMenu = flatMenuList.stream()
                        .filter(menu -> menu.getParent() == null)
                        .collect(Collectors.toList());

                flatMenuList.stream()
                        .filter(menu -> menu.getParent() != null)
                        .forEach(menu -> {
                            MenuVO parent = menuMap.get(menu.getParent());
                            if (parent != null) {
                                parent.getChildren().add(menu);
                            }
                        });

                menuMap.values().forEach(menu -> {
                    if (menu.getChildren().isEmpty()) {
                        menu.setChildren(null);
                    }
                });

                MENU_LIST = hierarchicalMenu;
            }
        }

        return MENU_LIST;
    }
}
