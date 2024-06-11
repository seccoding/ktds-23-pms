package com.ktdsuniversity.edu.pms.rentalsupply.service;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyListVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.SearchRentalSupplyVO;

public interface RentalSupplyService {

	/**
	 * 전체 대여품 리스트 조회
	 * @param searchRentalSupplyVO
	 * @return
	 */
	public RentalSupplyListVO searchAllRentalSupply(SearchRentalSupplyVO searchRentalSupplyVO);

	/**
	 * rsplId 조회 할 대여품의 상세 정보 조회
	 * @param rsplId 조회 할 대여품의 아이디
	 * @return
	 */
	public RentalSupplyVO getOneRentalSupply(String rsplId);
	
	/**
	 * 새 대여품 등록
	 * @param rentalSupplyVO
	 * @param file 대여품에 대한 이미지 파일
	 * @return
	 */
	public boolean registerNewRentalSupply(RentalSupplyVO rentalSupplyVO, MultipartFile file);
	
	/**
	 * 대여품 정보 수정
	 * @param rentalSupplyVO 수정 할 대여품 VO
	 * @return
	 */
	public boolean updateOneRentalSupply(RentalSupplyVO rentalSupplyVO);
	
	/**
	 * 대여품 재고 수정(현재 사용하지 않으며, 앞으로도 사용하지 않을 가능성이 높음)
	 * @param rentalSupplyVO 수정 할 대여품 VO
	 * @return
	 */
	public boolean updateOneRentalSupplyStock(RentalSupplyVO rentalSupplyVO);
	
	/**
	 * 대여품 삭제(논리적 삭제)
	 * @param rentalSupplyVO 수정 할 대여품 VO
	 * @return
	 */
	public boolean deleteOneRentalSupply(RentalSupplyVO rentalSupplyVO);
	
}
