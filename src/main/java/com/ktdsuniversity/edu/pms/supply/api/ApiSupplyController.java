package com.ktdsuniversity.edu.pms.supply.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.supply.service.SupplyService;
import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;

@RestController
@RequestMapping("/api/v1")
public class ApiSupplyController {
	
	@Autowired
	private SupplyService supplyService;
	
	@GetMapping("/supply/list")
	public ApiResponse getSupplyList(SearchSupplyVO searchSupplyVO) {
		SupplyListVO supplyListVO = this.supplyService.searchAllSupply(searchSupplyVO);
		
		return ApiResponse.Ok(supplyListVO.getSupplyList(), 
							  supplyListVO.getSupplyCnt(), 
							  searchSupplyVO.getPageCount(), 
							  searchSupplyVO.getPageNo() < searchSupplyVO.getPageCount() - 1);
	}
	
	@GetMapping("/supply/{splId}")
	public ApiResponse getSupply(@PathVariable String splId) {
		SupplyVO supplyVO = this.supplyService.getOneSupply(splId);
		
		return ApiResponse.Ok(supplyVO);
	}

}
