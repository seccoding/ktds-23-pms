package com.ktdsuniversity.edu.pms.supply.api;

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
import com.ktdsuniversity.edu.pms.supply.service.SupplyService;
import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyApprovalListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyApprovalVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyLogListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.ValidationUtils;

@RestController
@RequestMapping("/api/v1")
public class ApiSupplyController {

	@Autowired
	private SupplyService supplyService;

	@GetMapping("/supply")
	public ApiResponse getSupplyList(SearchSupplyVO searchSupplyVO) {
		SupplyListVO supplyListVO = this.supplyService.searchAllSupply(searchSupplyVO);

		return ApiResponse.Ok(supplyListVO.getSupplyList(), supplyListVO.getSupplyCnt(), searchSupplyVO.getPageCount(),
				searchSupplyVO.getPageNo() < searchSupplyVO.getPageCount() - 1);
	}

	@GetMapping("/supply/{splId}")
	public ApiResponse getSupply(@PathVariable String splId) {
		SupplyVO supplyVO = this.supplyService.getOneSupply(splId);

		return ApiResponse.Ok(supplyVO);
	}

	@GetMapping("/supply/category")
	public ApiResponse getSupplyCategoryList() {
		SupplyListVO supplyListVO = this.supplyService.getAllSupplyCategory();

		return ApiResponse.Ok(supplyListVO);
	}

	@GetMapping("/supply/image/{splImg}")
	public ApiResponse getSupplyImage(@PathVariable String splImg) {
		try {
			Path filePath = Paths.get("/usr/local/src/uploadfile/" + splImg);
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

	@PostMapping("/supply")
	public ApiResponse doSupplyRegistrationApprovalRequest(SupplyApprovalVO supplyApprovalVO,
			@RequestParam(required = false) MultipartFile file, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		if (!employeeVO.getDeptId().equals("DEPT_230101_000010")) {
			return ApiResponse.FORBIDDEN("접근 권한이 없습니다.");
		}

		boolean isNotEmptyName = ValidationUtils.notEmpty(supplyApprovalVO.getSplName());
		boolean isNotEmptyCategory = ValidationUtils.notEmpty(supplyApprovalVO.getSplCtgr());
		boolean isNotEmptyPrice = ValidationUtils.notEmpty(Integer.toString(supplyApprovalVO.getSplPrice()));
		boolean isNotEmptyDetail = ValidationUtils.notEmpty(supplyApprovalVO.getSplDtl());

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

		if (supplyApprovalVO.getSplPrice() < 0 || supplyApprovalVO.getInvQty() < 0) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("음수는 입력할 수 없습니다.");
		}

		if (errorMessage != null) {
			return ApiResponse.BAD_REQUEST(errorMessage);
		}

		supplyApprovalVO.setSplApprReqtr(employeeVO.getEmpId());

		boolean isRequestSuccess = this.supplyService.requestRegistrationNewSupply(supplyApprovalVO, file);

		return ApiResponse.Ok(isRequestSuccess);
	}

	@PutMapping("/supply/{splId}")
	public ApiResponse doSupplyModificationApprovalRequest(@PathVariable String splId,
			SupplyApprovalVO supplyApprovalVO, @RequestParam(required = false) MultipartFile file,
			Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		if (!employeeVO.getDeptId().equals("DEPT_230101_000010")) {
			return ApiResponse.FORBIDDEN("접근 권한이 없습니다.");
		}

		boolean isNotEmptyName = ValidationUtils.notEmpty(supplyApprovalVO.getSplName());
		boolean isNotEmptyCategory = ValidationUtils.notEmpty(supplyApprovalVO.getSplCtgr());
		boolean isNotEmptyPrice = ValidationUtils.notEmpty(Integer.toString(supplyApprovalVO.getSplPrice()));
		boolean isNotEmptyDetail = ValidationUtils.notEmpty(supplyApprovalVO.getSplDtl());

//		Validator<SupplyVO> validator = new Validator<>(supplyVO);
//		validator
//		.add("splName", Type.NOT_EMPTY, "제품명을 입력해 주세요.")
//		.start();
//		
//		if(validator.hasErrors()) {
//			return ApiResponse.BAD_REQUEST(Map.of("error",validator.getErrors()));
//		}

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

		supplyApprovalVO.setSplId(splId);
		supplyApprovalVO.setSplApprReqtr(employeeVO.getEmpId());

		boolean isUpdatedSuccess = this.supplyService.requestModificationSupply(supplyApprovalVO, file);

		return ApiResponse.Ok(isUpdatedSuccess);
	}

	@PutMapping("/supply/get")
	public ApiResponse doSupplyGetApprovalRequest(@RequestBody SupplyApprovalVO supplyApprovalVO,
			Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

		SupplyVO supplyVO = supplyService.getOneSupply(supplyApprovalVO.getSplId());
		
		if (supplyVO == null) {
			return ApiResponse.BAD_REQUEST(List.of("해당 소모품이 존재하지 않습니다."));
		}

		int requestedQty = supplyApprovalVO.getSplRqstQty();
		if (requestedQty <= 0) {
			return ApiResponse.BAD_REQUEST(List.of("올바른 신청 갯수를 입력해 주세요."));
		}

		int currentQty = supplyVO.getInvQty();
		if (requestedQty > currentQty) {
			return ApiResponse.BAD_REQUEST(List.of("재고가 부족합니다."));
		}
		supplyApprovalVO.setSplName(supplyVO.getSplName());
		supplyApprovalVO.setSplCtgr(supplyVO.getSplCtgr());
		supplyApprovalVO.setSplImg(supplyVO.getSplImg());
		supplyApprovalVO.setSplDtl(supplyVO.getSplDtl());
		supplyApprovalVO.setSplPrice(supplyVO.getSplPrice());
		// 차감 후 재고 설정
		supplyApprovalVO.setInvQty(currentQty - requestedQty);
		supplyApprovalVO.setSplApprReqtr(employeeVO.getEmpId());

		boolean isRequestSuccess = supplyService.requestGetSupply(supplyApprovalVO);

		return ApiResponse.Ok(isRequestSuccess);
	}

	@DeleteMapping("/supply/{splId}")
	public ApiResponse doSupplyDeleteApprovalRequest(@PathVariable String splId, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

		if (!employeeVO.getDeptId().equals("DEPT_230101_000010")) {
			return ApiResponse.FORBIDDEN("접근 권한이 없습니다.");
		}

		SupplyApprovalVO supplyApprovalVO = new SupplyApprovalVO();
		supplyApprovalVO.setSplId(splId);
		supplyApprovalVO.setSplApprReqtr(employeeVO.getEmpId());

		boolean isSuccess = this.supplyService.requestDeleteSupply(supplyApprovalVO);

		return ApiResponse.Ok(isSuccess);
	}

	@GetMapping("/supply/log")
	public ApiResponse getSupplyApprovalLogList(SearchSupplyVO searchSupplyVO, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		if (!employeeVO.getDeptId().equals("DEPT_230101_000010") && !employeeVO.getMngrYn().equals("Y")) {
			searchSupplyVO.setEmpId(employeeVO.getEmpId());
		}
			
		SupplyApprovalListVO supplyApprovalListVO = this.supplyService.searchAllSupplyApprovalLog(searchSupplyVO);

		return ApiResponse.Ok(supplyApprovalListVO.getSupplyApprovalList(), supplyApprovalListVO.getSupplyApprovalCnt(),
				searchSupplyVO.getPageCount(), searchSupplyVO.getPageNo() < searchSupplyVO.getPageCount() - 1);
	}
	
	@PostMapping("/supply/apply")
	public ApiResponse applyForMultipleSupplies(@RequestBody List<SupplyApprovalVO> supplyApprovalVOList,
	                                            Authentication authentication) {
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
	    
	    for (SupplyApprovalVO supplyApprovalVO : supplyApprovalVOList) {
	        // 각 소모품 신청에 대해 처리
	        supplyApprovalVO.setSplApprReqtr(employeeVO.getEmpId());
	        boolean isRequestSuccess = this.supplyService.requestGetSupply(supplyApprovalVO);
	        if (!isRequestSuccess) {
	            return ApiResponse.BAD_REQUEST(List.of("소모품 신청 처리에 실패했습니다."));
	        }
	    }
	    return ApiResponse.Ok(true);
	}

}
