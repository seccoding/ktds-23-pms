package com.ktdsuniversity.edu.pms.rentalsupply.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.rentalsupply.service.RentalSupplyService;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyListVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.SearchRentalSupplyVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;

@RestController
@RequestMapping("/api/v1")
public class ApiRentalSupplyController {

	@Autowired
	private RentalSupplyService rentalSupplyService;
	
	/**
	 * 대여품 목록 보여주는 기능
	 * @param searchRentalSupplyVO
	 * @return
	 */
	@GetMapping("/rentalsupply/list")
	public ApiResponse getRentalSupplyList(SearchRentalSupplyVO searchRentalSupplyVO) {
		RentalSupplyListVO rentalSupplyListVO = this.rentalSupplyService.searchAllRentalSupply(searchRentalSupplyVO);
		
		return ApiResponse.Ok(rentalSupplyListVO.getRentalSupplyList(), 
				rentalSupplyListVO.getRentalSupplyCnt(), 
				searchRentalSupplyVO.getPageCount(), 
				searchRentalSupplyVO.getPageNo() < searchRentalSupplyVO.getPageCount() - 1);
	}
	
}
