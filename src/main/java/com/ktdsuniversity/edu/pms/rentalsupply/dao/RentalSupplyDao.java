package com.ktdsuniversity.edu.pms.rentalsupply.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.SearchRentalSupplyVO;

public interface RentalSupplyDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.rentalsupply.dao.RentalSupplyDao";

	public int searchRentalSupplyAllCount(SearchRentalSupplyVO searchRentalSupplyVO);

	public List<RentalSupplyVO> searchAllRentalSupply(SearchRentalSupplyVO searchRentalSupplyVO);
	
	
}
