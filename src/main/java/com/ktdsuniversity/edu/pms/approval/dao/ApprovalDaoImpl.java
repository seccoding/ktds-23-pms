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
	public ApprovalVO getApprovalByApprId(String apprId) {
		return getSqlSession().selectOne(ApprovalDao.NAME_SPACE + ".getApprovalByApprId", apprId);
	}
	@Override
	public boolean insertApproval(List<String> approverList, ApprovalVO approvalVO) {
//		1. 파라미터로 받아온 정보로 insert 에 넣을 리스트 파라미터 생성
		List<ApprovalVO> apprList = new ArrayList<>();
		for (String  approver: approverList) {
			approvalVO.setApprover(approver);
			ApprovalVO temp = new ApprovalVO(approvalVO);
			apprList.add(temp);
		}
		
//		2. insert 실행후 리스트의 size 와 비교하여 일치하지 않는 경우 false 로 반환
		int insertCnt = getSqlSession().update(ApprovalDao.NAME_SPACE+".insertApproval", apprList);
		if(insertCnt != approverList.size()) {
			return false;
		}
		
//		3. insert 된 row 를 select 해와서 update 파라미터에 pid 를 세팅 
		apprList = new ArrayList<>();
		apprList=this.getAllInsertedApproval(approvalVO.getApprInfo());
		
//		4. update 실행후 insertCnt와 비교하여 일치하지 않는 경우 false로 반환
		int updateCnt = updateInsertedApproval(apprList);
		if(updateCnt != insertCnt) {
			return false;
		}
		return true;
	}

	@Override
	public int updateOneApproveal(ApprovalVO approvalVO) {
		
		return getSqlSession().update(ApprovalDao.NAME_SPACE+".updateOneApproveal", approvalVO);
	}
	
	@Override
	public int getNonApprCnt(ApprovalVO approvalVO) {
		return getSqlSession().selectOne(ApprovalDao.NAME_SPACE+".getNonApprCnt", approvalVO);
	}


	/**
	 * insertApproval 로 들어간 승인정보를 가져와서 pid를 업데이트 해준다
	 * @param apprList update 할 List
	 * @return
	 */
	private List<ApprovalVO> getAllInsertedApproval(String apprInfo){
		List<ApprovalVO> apprList =getSqlSession().selectList(ApprovalDao.NAME_SPACE+".getAllInsertedApproval", apprInfo);
		for (int i=1; i<apprList.size(); i++) {
			apprList.get(i).setpApprId(apprList.get(i-1).getApprId());
		}
		return apprList;
	};
	/**
	 * insert 된 row 의 pid 를 받아서 업데이트 한다
	 * @param apprList 업데이트 해야되는 정보(pApprId 포함되있음)
	 * @return 
	 */
	private int updateInsertedApproval(List<ApprovalVO> apprList) {
		return getSqlSession().update(ApprovalDao.NAME_SPACE+".updateInsertedApproval",apprList);
	}

	
	
	


}
