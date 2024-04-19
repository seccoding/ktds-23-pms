package com.ktdsuniversity.edu.pms.approval.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;


@Repository
public class ApprovalDaoImpl extends SqlSessionDaoSupport implements ApprovalDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int getAllCount() {
		return getSqlSession().selectOne(ApprovalDao.NAME_SPACE + ".getAllCount");
	}

	@Override
	public List<ApprovalVO> getAllApproval() {
		return getSqlSession().selectList(ApprovalDao.NAME_SPACE + ".getAllApproval");
	}

	@Override
	public int getAllCountByEmpId(String empId) {
		return getSqlSession().selectOne(ApprovalDao.NAME_SPACE + ".getAllCountByEmpId", empId);
	}
	
	@Override
	public List<ApprovalVO> getAllApprovalByEmpId(String empId) {
		return getSqlSession().selectList(ApprovalDao.NAME_SPACE + ".getAllApprovalByEmpId", empId);
	}

	@Override
	public ApprovalVO selectOneApproval(String apprId) {
		return getSqlSession().selectOne(ApprovalDao.NAME_SPACE + ".selectOneApproval", apprId);
	}

	@Override
	public ApprovalVO selectOneApprovalAll(String apprId) {
		return getSqlSession().selectOne(ApprovalDao.NAME_SPACE + ".selectOneApprovalAll", apprId);
	}

	@Override
	public int updateApprovalStatus(ApprovalVO approvalVO) {
		return getSqlSession().update(ApprovalDao.NAME_SPACE+".updateApprovalStatus", approvalVO);

	}

	@Override
	public int deleteApproval(String apprId) {
		return getSqlSession().update(ApprovalDao.NAME_SPACE +".deleteApproval", apprId);
	}

	@Override
	public int getAllApproveCount() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(ApprovalDao.NAME_SPACE+".getAllApproveCount");
	}

	@Override
	public int getAllOneWeekApprovalCount() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(ApprovalDao.NAME_SPACE+".getAllOneWeekApprovalCount");
	}

	@Override
	public int getAllMonthApprovalCount() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(ApprovalDao.NAME_SPACE+".getAllMonthApprovalCount");
	}

	@Override
	public int updateApproval(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().update(ApprovalDao.NAME_SPACE+".updateApproval",id);
	}
	
	
	@Override
	public int searchBoardAllCount(ApprovalVO approvaVo) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(ApprovalDao.NAME_SPACE+".searchBoardAllCount", approvaVo);
	}

	@Override
	public List<ApprovalVO> searchAllBoard(ApprovalVO approvaVo) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(ApprovalDao.NAME_SPACE+".searchAllBoard", approvaVo);
	}


}
