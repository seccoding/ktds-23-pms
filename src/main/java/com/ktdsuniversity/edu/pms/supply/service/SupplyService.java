package com.ktdsuniversity.edu.pms.supply.service;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyApprovalListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyApprovalVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyVO;

public interface SupplyService {

	/**
	 * 전체 소모품 리스트 조회
	 * @param searchSupplyVO
	 * @return
	 */
	public SupplyListVO searchAllSupply(SearchSupplyVO searchSupplyVO);

	/**
	 * splId로 조회 할 소모품의 상세 정보 조회
	 * @param splId 조회 할 소모품의 아이디
	 * @return
	 */
	public SupplyVO getOneSupply(String splId);
	
	public SupplyListVO getAllSupplyCategory();
	
	/**
	 * 새 소모품 등록 결재 요청
	 * @param supplyApprovalVO
	 * @param file
	 * @return
	 */
	public boolean requestRegistrationNewSupply(SupplyApprovalVO supplyApprovalVO, MultipartFile file);

	/**
	 * 소모품 정보 수정 요청
	 * @param supplyApprovalVO
	 * @param file
	 * @return
	 */
	public boolean requestModificationSupply(SupplyApprovalVO supplyApprovalVO, MultipartFile file);
	
	public boolean requestGetSupply(SupplyApprovalVO supplyApprovalVO);

	/**
	 * 소모품 재고 수정(현재 사용하지 않으며, 앞으로도 사용하지 않을 가능성이 높음)
	 * @param supplyVO 수정 할 소모품 VO
	 * @return
	 */
	public boolean updateOneSupplyStock(SupplyVO supplyVO);
	
	/**
	 * 소모품 삭제 요청
	 * @param supplyVO
	 * @return
	 */
	public boolean requestDeleteSupply(SupplyApprovalVO supplyApprovalVO);

	/**
	 * 소모품 신청 기록 조회
	 * @param searchSupplyVO
	 * @return
	 */
//	public SupplyLogListVO searchAllSupplyLog(SearchSupplyVO searchSupplyVO);

	public SupplyApprovalListVO searchAllSupplyApprovalLog(SearchSupplyVO searchSupplyVO);

}
