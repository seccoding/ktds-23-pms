package com.ktdsuniversity.edu.pms.rentalsupply.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.rentalsupply.service.RentalSupplyService;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyApprovalListVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyApprovalVO;
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
	 * 
	 * @param searchRentalSupplyVO
	 * @return
	 */
	@GetMapping("/rentalsupply")
	public ApiResponse getRentalSupplyList(SearchRentalSupplyVO searchRentalSupplyVO) {
		RentalSupplyListVO rentalSupplyListVO = this.rentalSupplyService.searchAllRentalSupply(searchRentalSupplyVO);

		return ApiResponse.Ok(rentalSupplyListVO.getRentalSupplyList(), rentalSupplyListVO.getRentalSupplyCnt(),
				searchRentalSupplyVO.getPageCount(),
				searchRentalSupplyVO.getPageNo() < searchRentalSupplyVO.getPageCount() - 1);
	}

	@GetMapping("/rentalsupply/{rsplId}")
	public ApiResponse getRentalSupply(@PathVariable String rsplId) {
		RentalSupplyVO rentalSupplyVO = this.rentalSupplyService.getOneRentalSupply(rsplId);

		return ApiResponse.Ok(rentalSupplyVO);
	}

	@GetMapping("/rentalsupply/category")
	public ApiResponse getRentalSupplyCategoryList() {
		RentalSupplyListVO rentalSupplyListVO = this.rentalSupplyService.getAllRentalSupplyCategory();

		return ApiResponse.Ok(rentalSupplyListVO);
	}

	@GetMapping("/rentalsupply/image/{rsplImg}")
	public ApiResponse getRentalSupplyImage(@PathVariable String rsplImg) {
		try {
			Path filePath = Paths.get("/usr/local/src/uploadfile/" + rsplImg);
			if (Files.exists(filePath)) {
				byte[] imageBytes = Files.readAllBytes(filePath);
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				return ApiResponse.Ok(base64Image);
			} else {
				return ApiResponse.BAD_REQUEST(List.of("이미지를 찾을 수 없습니다."));
			}
		} catch (IOException e) {
			return ApiResponse.BAD_REQUEST(List.of("이미지를 불러오는 데에 실패했습니다."));
		}
	}

	@PostMapping("/rentalsupply")
	public ApiResponse doRentalSupplyRegistrationApprovalRequest(RentalSupplyApprovalVO rentalSupplyApprovalVO,
			@RequestParam(required = false) MultipartFile file, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

		if (!employeeVO.getDeptId().equals("DEPT_230101_000010")) {
			return ApiResponse.FORBIDDEN("접근 권한이 없습니다.");
		}

		boolean isNotEmptyName = ValidationUtils.notEmpty(rentalSupplyApprovalVO.getRsplName());
		boolean isNotEmptyCategory = ValidationUtils.notEmpty(rentalSupplyApprovalVO.getRsplCtgr());
		boolean isNotEmptyPrice = ValidationUtils.notEmpty(Integer.toString(rentalSupplyApprovalVO.getRsplPrice()));
		boolean isNotEmptyDetail = ValidationUtils.notEmpty(rentalSupplyApprovalVO.getRsplDtl());

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

		if (!isNotEmptyDetail) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("제품 설명을 입력해 주세요.");
		}

		if (rentalSupplyApprovalVO.getRsplPrice() < 0 || rentalSupplyApprovalVO.getInvQty() < 0) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("음수는 입력할 수 없습니다.");
		}

		if (errorMessage != null) {
			return ApiResponse.BAD_REQUEST(errorMessage);
		}

		rentalSupplyApprovalVO.setRsplApprReqtr(employeeVO.getEmpId());

		boolean isRequestSuccess = this.rentalSupplyService.requestRegistrationNewRentalSupply(rentalSupplyApprovalVO,
				file);

		return ApiResponse.Ok(isRequestSuccess);
	}

	@PutMapping("/rentalsupply/{rsplId}")
	public ApiResponse doRentalSupplyModificationApprovalRequest(@PathVariable String rsplId,
			RentalSupplyApprovalVO rentalSupplyApprovalVO, @RequestParam(required = false) MultipartFile file,
			Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

		if (!employeeVO.getDeptId().equals("DEPT_230101_000010")) {
			return ApiResponse.FORBIDDEN("접근 권한이 없습니다.");
		}

		boolean isNotEmptyName = ValidationUtils.notEmpty(rentalSupplyApprovalVO.getRsplName());
		boolean isNotEmptyCategory = ValidationUtils.notEmpty(rentalSupplyApprovalVO.getRsplCtgr());
		boolean isNotEmptyPrice = ValidationUtils.notEmpty(Integer.toString(rentalSupplyApprovalVO.getRsplPrice()));
		boolean isNotEmptyDetail = ValidationUtils.notEmpty(rentalSupplyApprovalVO.getRsplDtl());

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

		if (!isNotEmptyDetail) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("제품 설명을 입력해 주세요.");
		}

		if (errorMessage != null) {
			return ApiResponse.BAD_REQUEST(errorMessage);
		}

		rentalSupplyApprovalVO.setRsplId(rsplId);
		rentalSupplyApprovalVO.setRsplMdfrId(employeeVO.getEmpId());

		boolean isUpdatedSuccess = this.rentalSupplyService.requestModificationRentalSupply(rentalSupplyApprovalVO,
				file);

		return ApiResponse.Ok(isUpdatedSuccess);
	}

	@PutMapping("/rentalsupply/get")
	public ApiResponse doRentalSupplyGetApprovalRequest(@RequestBody RentalSupplyApprovalVO rentalSupplyApprovalVO,
			Authentication authentication) {
		rentalSupplyApprovalVO.setRtrnYn("N");
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

		RentalSupplyVO rentalSupplyVO = rentalSupplyService.getOneRentalSupply(rentalSupplyApprovalVO.getRsplId());

		if (rentalSupplyVO == null) {
			return ApiResponse.BAD_REQUEST(List.of("해당 대여품이 존재하지 않습니다."));
		}

		int requestedQty = rentalSupplyApprovalVO.getRsplRqstQty();
		if (requestedQty <= 0) {
			return ApiResponse.BAD_REQUEST(List.of("올바른 신청 갯수를 입력해 주세요."));
		}

		int currentQty = rentalSupplyVO.getInvQty();
		if (requestedQty > currentQty) {
			return ApiResponse.BAD_REQUEST(List.of("재고가 부족합니다."));
		}
		rentalSupplyApprovalVO.setRsplName(rentalSupplyVO.getRsplName());
		rentalSupplyApprovalVO.setRsplCtgr(rentalSupplyVO.getRsplCtgr());
		rentalSupplyApprovalVO.setRsplImg(rentalSupplyVO.getRsplImg());
		rentalSupplyApprovalVO.setRsplDtl(rentalSupplyVO.getRsplDtl());
		rentalSupplyApprovalVO.setRsplPrice(rentalSupplyVO.getRsplPrice());
		// 차감 후 재고 설정
		rentalSupplyApprovalVO.setInvQty(currentQty - requestedQty);
		rentalSupplyApprovalVO.setRsplApprReqtr(employeeVO.getEmpId());

		boolean isRequestSuccess = rentalSupplyService.requestGetRentalSupply(rentalSupplyApprovalVO);

		return ApiResponse.Ok(isRequestSuccess);
	}

	@DeleteMapping("/rentalsupply/{rsplId}")
	public ApiResponse doRentalSupplyDeleteApprovalRequest(@PathVariable String rsplId, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

		if (!employeeVO.getDeptId().equals("DEPT_230101_000010")) {
			return ApiResponse.FORBIDDEN("접근 권한이 없습니다.");
		}

		RentalSupplyApprovalVO rentalSupplyApprovalVO = new RentalSupplyApprovalVO();
		rentalSupplyApprovalVO.setRsplId(rsplId);
		rentalSupplyApprovalVO.setRsplApprReqtr(employeeVO.getEmpId());

		boolean isSuccess = this.rentalSupplyService.requestDeleteRentalSupply(rentalSupplyApprovalVO);

		return ApiResponse.Ok(isSuccess);
	}

	@GetMapping("/rentalsupply/log")
	public ApiResponse getRentalSupplyApprovalLogList(SearchRentalSupplyVO searchRentalSupplyVO,
			Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

		if (!employeeVO.getDeptId().equals("DEPT_230101_000010") && !employeeVO.getMngrYn().equals("Y")) {
			searchRentalSupplyVO.setEmpId(employeeVO.getEmpId());
		}

		RentalSupplyApprovalListVO rentalSupplyApprovalListVO = this.rentalSupplyService
				.searchAllRentalSupplyApprovalLog(searchRentalSupplyVO);

		return ApiResponse.Ok(rentalSupplyApprovalListVO.getRentalSupplyApprovalList(),
				rentalSupplyApprovalListVO.getRentalSupplyApprovalCnt(), searchRentalSupplyVO.getPageCount(),
				searchRentalSupplyVO.getPageNo() < searchRentalSupplyVO.getPageCount() - 1);
	}

	@PostMapping("/rentalsupply/apply")
	public ApiResponse applyForMultipleRentalSupplies(
			@RequestBody List<RentalSupplyApprovalVO> rentalSupplyApprovalVOList, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

		for (RentalSupplyApprovalVO rentalSupplyApprovalVO : rentalSupplyApprovalVOList) {
			// 각 소모품 신청에 대해 처리
			rentalSupplyApprovalVO.setRsplApprReqtr(employeeVO.getEmpId());
			boolean isRequestSuccess = this.rentalSupplyService.requestGetRentalSupply(rentalSupplyApprovalVO);
			if (!isRequestSuccess) {
				return ApiResponse.BAD_REQUEST(List.of("대여품 신청 처리에 실패했습니다."));
			}
		}
		return ApiResponse.Ok(true);
	}

	@PostMapping("/rentalsupply/return")
	public ApiResponse doRentalSupplyReturnApprovalRequest(@RequestBody RentalSupplyApprovalVO rentalSupplyApprovalVO,
			Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

		if (!employeeVO.getDeptId().equals("DEPT_230101_000010")) {
			return ApiResponse.FORBIDDEN("접근 권한이 없습니다.");
		}

		boolean isReturnSuccess = this.rentalSupplyService.requestReturnRentalSupply(rentalSupplyApprovalVO);
		
	    if (isReturnSuccess) {
	        return ApiResponse.Ok(true);
	    } else {
	        return ApiResponse.BAD_REQUEST("반납 요청 처리에 실패했습니다.");
	    }
	}
}
