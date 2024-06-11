package com.ktdsuniversity.edu.pms.rentalsupply.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.rentalsupply.service.RentalSupplyService;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyListVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.SearchRentalSupplyVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.ValidationUtils;

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
	@GetMapping("/rentalsupply")
	public ApiResponse getRentalSupplyList(SearchRentalSupplyVO searchRentalSupplyVO) {
		RentalSupplyListVO rentalSupplyListVO = this.rentalSupplyService.searchAllRentalSupply(searchRentalSupplyVO);
		
		return ApiResponse.Ok(rentalSupplyListVO.getRentalSupplyList(), 
				rentalSupplyListVO.getRentalSupplyCnt(), 
				searchRentalSupplyVO.getPageCount(), 
				searchRentalSupplyVO.getPageNo() < searchRentalSupplyVO.getPageCount() - 1);
	}
	
	@GetMapping("/rentalsupply/{rsplId}")
	public ApiResponse getRentalSupply(@PathVariable String rsplId) {
		RentalSupplyVO rentalSupplyVO = this.rentalSupplyService.getOneRentalSupply(rsplId);
		
		return ApiResponse.Ok(rentalSupplyVO);
	}
	
	@PostMapping("/rentalsupply")
	public ApiResponse doRentalSupplyRegistration(RentalSupplyVO rentalSupplyVO, 
												  @RequestParam(required = false) MultipartFile file, 
												  Authentication authentication) {
		boolean isNotEmptyName = ValidationUtils.notEmpty(rentalSupplyVO.getRsplName());
		boolean isNotEmptyCategory = ValidationUtils.notEmpty(rentalSupplyVO.getRsplCtgr());
		boolean isNotEmptyPrice = ValidationUtils.notEmpty(Integer.toString(rentalSupplyVO.getRsplPrice()));
		boolean isNotEmptyImage= file != null && !file.isEmpty();
		boolean isNotEmptyDetail = ValidationUtils.notEmpty(rentalSupplyVO.getRsplDtl());
		
		List<String> errorMessage = null;
		
		if (!isNotEmptyName) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("제품명을 입력해 주세요.");
		}
		
		if (!isNotEmptyCategory) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("제품 카테고리를 입력해 주세요.");
		}
		
		if (!isNotEmptyPrice) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("제품 가격을 입력해 주세요.");
		}
		
		if (!isNotEmptyImage) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("제품 이미지를 삽입해 주세요.");
		}
		
		if (!isNotEmptyDetail) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("제품 설명을 입력해 주세요.");
		}
		
		if (errorMessage != null) {
			return ApiResponse.BAD_REQUEST(errorMessage);
		}
		
		rentalSupplyVO.setRsplRegtId("system01");
		
		boolean isCreateSuccess = this.rentalSupplyService.registerNewRentalSupply(rentalSupplyVO, file);
		
		return ApiResponse.Ok(isCreateSuccess);
	}
	
	@PutMapping("/rentalsupply/{rsplId}")
	public ApiResponse doRentalSupplyModify(@PathVariable String rsplId,
											RentalSupplyVO rentalSupplyVO,
											@RequestParam(required = false) MultipartFile file,
											Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		boolean isNotEmptyName = ValidationUtils.notEmpty(rentalSupplyVO.getRsplName());
		boolean isNotEmptyCategory = ValidationUtils.notEmpty(rentalSupplyVO.getRsplCtgr());
		boolean isNotEmptyPrice = ValidationUtils.notEmpty(Integer.toString(rentalSupplyVO.getRsplPrice()));
		boolean isNotEmptyImage= file != null && !file.isEmpty();
		boolean isNotEmptyDetail = ValidationUtils.notEmpty(rentalSupplyVO.getRsplDtl());
		
List<String> errorMessage = null;
		
		if (!isNotEmptyName) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("제품명을 입력해 주세요.");
		}
		
		if (!isNotEmptyCategory) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("제품 카테고리를 입력해 주세요.");
		}
		
		if (!isNotEmptyPrice) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("제품 가격을 입력해 주세요.");
		}
		
		if (!isNotEmptyImage) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("제품 이미지를 삽입해 주세요.");
		}
		
		if (!isNotEmptyDetail) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("제품 설명을 입력해 주세요.");
		}
		
		if (errorMessage != null) {
			return ApiResponse.BAD_REQUEST(errorMessage);
		}
		
		rentalSupplyVO.setRsplId(rsplId);
		rentalSupplyVO.setRsplMdfrId(employeeVO.getEmpId());
		
		boolean isUpdatedSuccess = this.rentalSupplyService.updateOneRentalSupply(rentalSupplyVO);
		
		return ApiResponse.Ok(isUpdatedSuccess);
	}
	
	@PutMapping("/rentalsupply/stock/{rsplId}")
	public ApiResponse modifyRentalSupplyStock(@PathVariable String rsplId,
											   RentalSupplyVO rentalSupplyVO,
											   Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		rentalSupplyVO.setRsplId(rsplId);
		rentalSupplyVO.setRsplMdfrId(employeeVO.getEmpId());
		
		boolean isUpdatedSuccess = this.rentalSupplyService.updateOneRentalSupplyStock(rentalSupplyVO);
		
		return ApiResponse.Ok(isUpdatedSuccess);
	}
	
	@DeleteMapping("/rentalsupply/{rsplId}")
	public ApiResponse deleteRentalSupply(@PathVariable String rsplId,
										  Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		RentalSupplyVO rentalSupplyVO = this.rentalSupplyService.getOneRentalSupply(rsplId);
		
		rentalSupplyVO.setRsplMdfrId(employeeVO.getEmpId());
		
		boolean isSuccess = this.rentalSupplyService.deleteOneRentalSupply(rentalSupplyVO);
		
		return ApiResponse.Ok(isSuccess);
	}
}
