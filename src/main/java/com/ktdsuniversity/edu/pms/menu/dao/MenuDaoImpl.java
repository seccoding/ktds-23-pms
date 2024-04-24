package com.ktdsuniversity.edu.pms.menu.dao;

import com.ktdsuniversity.edu.pms.menu.vo.MenuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuDaoImpl extends SqlSessionDaoSupport implements MenuDao {

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public List<MenuVO> selectAllMenuList() {
        return getSqlSession().selectList(NAME_SPACE + ".selectAllMenuList");
    }

    @Override
    public List<MenuVO> selectAllHierarchicalMenuList() {
        return getSqlSession().selectList(NAME_SPACE + ".selectAllHierarchicalMenuList");
    }

    @Override
    public int insertNewMenu(MenuVO menuVO) {
        return getSqlSession().insert(NAME_SPACE + ".insertNewMenu", menuVO);

    }

    @Override
    public int updateMenu(MenuVO menuVO) {
        return getSqlSession().update(NAME_SPACE + ".updateMenu", menuVO);
    }

    @Override
    public int deleteMenu(String id) {
        return getSqlSession().update(NAME_SPACE + ".deleteMenu", id);
    }
}
