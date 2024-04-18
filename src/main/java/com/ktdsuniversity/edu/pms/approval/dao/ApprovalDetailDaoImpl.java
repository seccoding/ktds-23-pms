package com.ktdsuniversity.edu.pms.approval.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailVO;



@Repository
public class ApprovalDetailDaoImpl extends SqlSessionDaoSupport implements ApprovalDetailDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<ApprovalDetailVO> getAllApprovalDetail() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(ApprovalDetailDao.NAME_SPACE+".getAllApprovalDetail");
	}
	
	@Override
	public int insertApproval(ApprovalDetailVO approvaldetailVo) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(ApprovalDetailDao.NAME_SPACE+".insertNewBoard", approvaldetailVo);
	}

	@Override
	public List<ApprovalDetailVO> getpersonApproval(String id) {
		// TODO Auto-generated method stub

		return getSqlSession().selectList(ApprovalDetailDao.NAME_SPACE+".getpersonApproval", id);
	}
	
	
}
