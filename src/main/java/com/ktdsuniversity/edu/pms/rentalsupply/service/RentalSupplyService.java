package com.ktdsuniversity.edu.pms.rentalsupply.service;

import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyListVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.SearchRentalSupplyVO;

public interface RentalSupplyService {

	RentalSupplyListVO searchAllRentalSupply(SearchRentalSupplyVO searchRentalSupplyVO);

	
}
