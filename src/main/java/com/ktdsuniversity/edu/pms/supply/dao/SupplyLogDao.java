package com.ktdsuniversity.edu.pms.supply.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyLogVO;

public interface SupplyLogDao {
	
	public String NAME_SPACE ="com.ktdsuniversity.edu.pms.supply.dao.SupplyLogDao";

	/**
	 * 전체 소모품 신청 기록의 수
	 * @param searchSupplyVO
	 * @return 카운트 한 소모품 신청 기록의 수
	 */
	public int searchSupplyLogAllCount(SearchSupplyVO searchSupplyVO);

	/**
	 * 전체 소모품 신청 기록 리스트
	 * @param searchSupplyVO
	 * @return 전체 소모품 신청 기록 리스트
	 */
	public List<SupplyLogVO> searchAllSupplyLog(SearchSupplyVO searchSupplyVO);

}
