package com.ktdsuniversity.edu.pms.supply.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyApprovalVO;

public interface SupplyApprovalDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.supply.dao.SupplyApprovalDao";

	public int searchSupplyAllApprovalLogCount(SearchSupplyVO searchSupplyVO);
	
	public List<SupplyApprovalVO> searchAllApprovalLog(SearchSupplyVO searchSupplyVO);
	
	public int insertSupplyApprovalRequest(SupplyApprovalVO supplyApprovalVO);
	
	public SupplyApprovalVO getSupplyApprovalByPK(String splApprId);
	
	/**
	 * PK를 던져주면 해당 Row의 SPL_APPR_YN을 Y를 업데이트 한다.
	 * @param splApprId 바꾸고자 하는 Row의 PK
	 * @return
	 */
	public int updateOneSupplyApprovalYnToYByPK(String splApprId);

}
