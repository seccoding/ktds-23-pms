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
	public List<CommuteVO> getAllCommuteData( CommuteVO commuteVO) {
		return getSqlSession().selectList(CommuteDao.COMMUTE_SPACE + ".getAllCommuteData", commuteVO);
	}

	@Override
	public List<CommuteVO> getAllCommuteDataByEmpId(CommuteVO commuteVO) {
		return getSqlSession().selectList(CommuteDao.COMMUTE_SPACE + ".getAllCommuteDataByEmpId", commuteVO);
	}

	@Override
	public CommuteVO getOneCommuteDataByEmpIdToday(String empId) {
		return getSqlSession().selectOne(CommuteDao.COMMUTE_SPACE + ".getOneCommuteDataByEmpIdToday", empId);
	}

	@Override
	public int insertCommuteIn(String empId) {
		return getSqlSession().insert(CommuteDao.COMMUTE_SPACE +".insertCommuteIn", empId);
	}

	@Override
	public int updateCommuteLeaveWork(String empId) {
		return getSqlSession().update(CommuteDao.COMMUTE_SPACE+".updateCommuteLeaveWork", empId);
	}

}
