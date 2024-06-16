package com.ktdsuniversity.edu.pms.rentalsupply.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.SearchRentalSupplyVO;

public interface RentalSupplyDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.rentalsupply.dao.RentalSupplyDao";

	/**
	 * 전체 대여품의 수
	 * @param searchRentalSupplyVO
	 * @return 카운트 한 대여품의 수
	 */
	public int searchRentalSupplyAllCount(SearchRentalSupplyVO searchRentalSupplyVO);

	/**
	 * 전체 대여품 리스트
	 * @param searchRentalSupplyVO
	 * @return 전체 대여품 정보 리스트
	 */
	public List<RentalSupplyVO> searchAllRentalSupply(SearchRentalSupplyVO searchRentalSupplyVO);
	
	/**
	 * rsplId로 조회된 대여품의 상세 정보
	 * @param rsplId 대여품에 대한 아이디
	 * @return 조회한 대여품의 상세 정보
	 */
	public RentalSupplyVO selectOneRentalSupply(String rsplId);
	
	public List<RentalSupplyVO> selectAllRentalSupplyCategory();
	
	/**
	 * 새 대여품 등록
	 * @param rentalSupplyVO
	 * @return insert 성공횟수 (1이면 성공, 0이면 실패)
	 */
	public int registerNewRentalSupply(RentalSupplyVO rentalSupplyVO);
	
	/**
	 * 등록 된 대여품 정보 수정
	 * @param rentalSupplyVO 수정 할 대여품 VO
	 * @return update 성공횟수 (1이면 성공, 0이면 실패)
	 */
	public int updateOneRentalSupply(RentalSupplyVO rentalSupplyVO);
	
	/**
	 * 등록 된 대여품의 재고 변경(현재 사용하지 않으며, 앞으로도 사용하지 않을 가능성이 높음)
	 * @param rentalSupplyVO 수정 할 대여품 VO
	 * @return update 성공횟수 (1이면 성공, 0이면 실패)
	 */
	public int updateOneRentalSupplyStock(RentalSupplyVO rentalSupplyVO);
	
	/**
	 * 대여품 삭제(논리적 삭제 => delYn을 N에서 Y로)
	 * @param rentalSupplyVO 삭제 할 대여품 VO
	 * @return update 성공횟수 (1이면 성공, 0이면 실패)
	 */
	public int deleteOneRentalSupply(RentalSupplyVO rentalSupplyVO);
	
}
