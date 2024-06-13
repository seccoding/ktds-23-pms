package com.ktdsuniversity.edu.pms.supply.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyApprovalVO;

public interface SupplyApprovalDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.supply.dao.SupplyApprovalDao";

	public int searchSupplyAllApprovalLogCount(SearchSupplyVO searchSupplyVO);
	
	public List<SupplyApprovalVO> searchAllApprovalLog(SearchSupplyVO searchSupplyVO);
	
	public int insertSupplyApprovalRequest(SupplyApprovalVO supplyApprovalVO);

}
