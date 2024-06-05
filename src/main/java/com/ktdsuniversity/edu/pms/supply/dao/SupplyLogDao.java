package com.ktdsuniversity.edu.pms.supply.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyLogVO;

public interface SupplyLogDao {
	
	public String NAME_SPACE ="com.ktdsuniversity.edu.pms.supplies.dao.SupplyLogDao";

	public int searchSupplyLogAllCount(SearchSupplyVO searchSupplyVO);

	public List<SupplyLogVO> searchAllSupplyLog(SearchSupplyVO searchSupplyVO);

}
