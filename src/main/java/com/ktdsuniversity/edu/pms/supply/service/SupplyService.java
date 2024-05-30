package com.ktdsuniversity.edu.pms.supply.service;

import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyListVO;

public interface SupplyService {

	SupplyListVO searchAllSupply(SearchSupplyVO searchSupplyVO);

}
