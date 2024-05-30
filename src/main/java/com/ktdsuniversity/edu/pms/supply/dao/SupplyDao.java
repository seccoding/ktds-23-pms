package com.ktdsuniversity.edu.pms.supply.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyVO;

public interface SupplyDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.supplies.dao.SupplyDao";

	public int searchSupplyAllCount(SearchSupplyVO searchSupplyVO);

	public List<SupplyVO> searchAllSupply(SearchSupplyVO searchSupplyVO);

}
