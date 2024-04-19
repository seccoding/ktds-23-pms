package com.ktdsuniversity.edu.pms.login.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;

@Repository
public class CommuteDaoImpl extends SqlSessionDaoSupport implements CommuteDao {

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public List<CommuteVO> getAllCommuteData() {
		return getSqlSession().selectList(CommuteDao.COMMUTE_SPACE + ".getAllCommuteData");
	}

	@Override
	public List<CommuteVO> getAllCommuteDataByEmpId() {
		return getSqlSession().selectList(CommuteDao.COMMUTE_SPACE + ".getAllCommuteData");
	}



	
}
