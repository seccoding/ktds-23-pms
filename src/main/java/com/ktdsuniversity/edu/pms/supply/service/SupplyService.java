package com.ktdsuniversity.edu.pms.supply.service;

import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyVO;

public interface SupplyService {

	public SupplyListVO searchAllSupply(SearchSupplyVO searchSupplyVO);

	public SupplyVO getOneSupply(String splId);

	public boolean registerNewSupply(SupplyVO supplyVO);

}
