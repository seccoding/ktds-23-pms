package com.ktdsuniversity.edu.pms.supply.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyVO;

public interface SupplyDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.supply.dao.SupplyDao";

	/**
	 * 전체 소모품의 수
	 * @param searchSupplyVO
	 * @return 카운트 한 소모품의 수
	 */
	public int searchSupplyAllCount(SearchSupplyVO searchSupplyVO);
	
	/**
	 * 전체 소모품 리스트
	 * @param searchSupplyVO
	 * @return 전체 소모품 정보 리스트
	 */
	public List<SupplyVO> searchAllSupply(SearchSupplyVO searchSupplyVO);

	/**
	 * splId로 조회된 소모품의 상세 정보
	 * @param splId 소모품에 대한 아이디
	 * @return 조회한 소모품의 상세 정보
	 */
	public SupplyVO selectOneSupply(String splId);
	
	/**
	 * 소모품의 전체 카테고리
	 * @param supplyVO
	 * @return 소모품 카테고리 리스트
	 */
	public List<SupplyVO> selectAllSupplyCategory();

	/**
	 * 새 소모품 등록
	 * @param supplyVO
	 * @return insert 성공횟수 (1이면 성공, 0이면 실패)
	 */
	public int registerNewSupply(SupplyVO supplyVO);

	/**
	 * 등록 된 소모품 정보 수정
	 * @param supplyVO 수정 할 소모품 VO
	 * @return update 성공횟수 (1이면 성공, 0이면 실패)
	 */
	public int updateOneSupply(SupplyVO supplyVO);

	/**
	 * 등록 된 소모품의 재고 변경(현재 사용하지 않으며, 앞으로도 사용하지 않을 가능성이 높음)
	 * @param supplyVO 수정 할 소모품 VO
	 * @return update 성공횟수 (1이면 성공, 0이면 실패)
	 */
	public int updateOneSupplyStock(SupplyVO supplyVO);
	
	/**
	 * 소모품 삭제(논리적 삭제 => delYn을 N에서 Y로)
	 * @param supplyVO 삭제 할 소모품 VO
	 * @return update 성공횟수 (1이면 성공, 0이면 실패)
	 */
	public int deleteOneSupply(SupplyVO supplyVO);

}
