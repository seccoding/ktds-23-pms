package com.ktdsuniversity.edu.pms.supply.service;

import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyVO;

public interface SupplyService {

	SupplyListVO searchAllSupply(SearchSupplyVO searchSupplyVO);

	SupplyVO getOneSupply(String splId);

}
