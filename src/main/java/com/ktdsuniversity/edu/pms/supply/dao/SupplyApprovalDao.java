package com.ktdsuniversity.edu.pms.supply.dao;

import com.ktdsuniversity.edu.pms.supply.vo.SupplyApprovalVO;

public interface SupplyApprovalDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.supply.dao.SupplyApprovalDao";

	public int insertSupplyRegistrationRequest(SupplyApprovalVO supplyApprovalVO);

}
