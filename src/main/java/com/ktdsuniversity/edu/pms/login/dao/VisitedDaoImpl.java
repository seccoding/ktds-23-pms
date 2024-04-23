package com.ktdsuniversity.edu.pms.login.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.login.vo.VisitedVO;

@Repository
public class VisitedDaoImpl extends SqlSessionDaoSupport implements VisitedDao{

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public int insertOneEmpVisitedHistory(VisitedVO visitedVO) {
		return getSqlSession().update(VisitedDao.VISITED_SPACE + ".insertOneEmpVisitedHistory", visitedVO);
	}
}
