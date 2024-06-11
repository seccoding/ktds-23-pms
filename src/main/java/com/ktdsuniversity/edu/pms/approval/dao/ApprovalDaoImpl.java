package com.ktdsuniversity.edu.pms.approval.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public boolean insertApproval(List<String> approverList, ApprovalVO approvalVO) {
		List<ApprovalVO> apprList = new ArrayList<>();
		for (String  approver: approverList) {
			approvalVO.setApprover(approver);
			apprList.add(approvalVO);
		}
		int insertCnt = getSqlSession().update(ApprovalDao.NAME_SPACE+".insertApproval", apprList);
		
//		TODO P ID UPDATE 혹은 쿼리 변환
		return insertCnt == approverList.size();
	}

	@Override
	public int updateOneApproveal(ApprovalVO approvalVO) {
		
//		TODO approvalVO.apprId 값의 pid 가 있을경우 해당 내용을 보이게 설정 
		return getSqlSession().update(ApprovalDao.NAME_SPACE+".updateOneApproveal", approvalVO);
	}



	


}
