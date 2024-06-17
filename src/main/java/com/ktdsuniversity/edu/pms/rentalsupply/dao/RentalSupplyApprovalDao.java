package com.ktdsuniversity.edu.pms.rentalsupply.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyApprovalVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.SearchRentalSupplyVO;

public interface RentalSupplyApprovalDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.rentalsupply.dao.RentalSupplyApprovalDao";
	
	public int searchRentalSupplyAllApprovalLogCount(SearchRentalSupplyVO searchRentalSupplyVO);
	
	public List<RentalSupplyApprovalVO> searchAllApprovalLog(SearchRentalSupplyVO searchRentalSupplyVO);
	
	public int insertRentalSupplyApprovalRequest(RentalSupplyApprovalVO rentalSupplyApprovalVO);
	
	public RentalSupplyApprovalVO getRentalSupplyApprovalByPK(String rsplApprId);
	
	public int updateOneRentalSupplyApprovalYnToYByPK(String rsplApprId);
	
	public int updateOneRentalSupplyForReturn(RentalSupplyApprovalVO rentalSupplyApprovalVO);

}
