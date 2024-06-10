package com.ktdsuniversity.edu.pms.employee.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.changehistory.service.ChangeHistoryService;
import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.JobHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.PositionHistoryVO;
import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeDataVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;
import com.ktdsuniversity.edu.pms.employee.web.EmployeeController;
import com.ktdsuniversity.edu.pms.job.service.JobService;
import com.ktdsuniversity.edu.pms.job.vo.JobVO;
import com.ktdsuniversity.edu.pms.team.service.TeamService;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.ValidationUtils;

@RestController
@RequestMapping("/api/v1")
public class ApiEmployeeController {

	private Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private ChangeHistoryService changeHistoryService;	

	@Autowired
	private JobService jobService;
	
	@Autowired
	private FileHandler fileHandler;
	
	private CommonCodeService commonCodeService;
	
	
	// 사원 리스트
	@GetMapping("/employee")
	public ApiResponse getEmployeeList(SearchEmployeeVO searchEmployeeVO) {
		
		EmployeeListVO employeeListVO = this.employeeService.searchAllEmployee(searchEmployeeVO);
		
		return ApiResponse.Ok(employeeListVO.getEmployeeList(), employeeListVO.getEmployeeCnt(),
								searchEmployeeVO.getPageCount(), searchEmployeeVO.getPageNo() < searchEmployeeVO.getPageCount() -1);
	}
	
	// 사원 상세 조회
	@GetMapping("/employee/view/{empId}")
	public ApiResponse getOneEmployee(@PathVariable String empId, Authentication authentication) {
		
		EmployeeVO employeeVO = this.employeeService.getOneEmployee(empId);
		
		// 변경이력 TODO@@!!
//		PaHistoryVO paHistoryVO = this.
//		List<JobVO> jobList = this.changeHistoryService.getAllJob();
//		List<CommonCodeVO> positionList = this.changeHistoryService.getAllPosition();
		
		return ApiResponse.Ok(employeeVO);
	}
	
	// 부서, 팀, 직무, 직급 리스트 조회
	@GetMapping("/employee/data")
	public ApiResponse getDataList() {
		var result = new HashMap<String, List<EmployeeDataVO>>();
		List<EmployeeDataVO> deptList = this.employeeService.getDepartList();
		List<EmployeeDataVO> teamList = this.employeeService.getTeamList();
		List<EmployeeDataVO> jobList = this.employeeService.getJobList();
		List<EmployeeDataVO> gradeList = this.employeeService.getEmployeeGradeList();
		
		result.put("depart", deptList);
		result.put("team", teamList);
		result.put("job", jobList);
		result.put("grade", gradeList.stream().filter(grade -> {
            return Integer.parseInt(grade.getDataId()) <= 110;
    }).toList());
		
		return ApiResponse.Ok(result);
	}
	
	
	// 수정
	@PutMapping("/employee/modify/{empId}")
	public ApiResponse domodifyEmployee(@RequestBody EmployeeVO employeeVO,
										@PathVariable("empId") String empId,
										MultipartFile file,
										Authentication authentication) {
		
		
		
		boolean isNotEmptyJobId = ValidationUtils.notEmpty( employeeVO.getJobId());
		boolean isNotEmptyDeptId = ValidationUtils.notEmpty( employeeVO.getDeptId());
		boolean isNotEmptyPstnId = ValidationUtils.notEmpty( employeeVO.getPstnId());
		boolean isNotEmptyWorkSts = ValidationUtils.notEmpty( employeeVO.getWorkSts());
		boolean isNotEmptyHireYear = ValidationUtils.notEmpty( employeeVO.getHireYear());
		boolean isNotEmptyAddr = ValidationUtils.notEmpty( employeeVO.getAddr());
		boolean isNotEmptyBirth = ValidationUtils.notEmpty( employeeVO.getBrth());
		boolean isNotEmptyEmail = ValidationUtils.notEmpty( employeeVO.getEmail());
		boolean isNotEmptyPwd = ValidationUtils.notEmpty( employeeVO.getPwd());
		boolean isNotEmptyConfirmPwd = ValidationUtils.notEmpty( employeeVO.getConfirmPwd());
		boolean isNotEmptyMngrYn = ValidationUtils.notEmpty( employeeVO.getMngrYn());
		
		
		List<String> errorMessage = null;
		 
		if (! isNotEmptyJobId) {
			
			if(errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("직무를 입력해주세요.");
		}

		if (! isNotEmptyDeptId) {
			if(errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("부서를 입력해주세요.");
		}
		
		if (! isNotEmptyPstnId) {
			if(errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("직급를 입력해주세요.");
		}
		
		if (! isNotEmptyWorkSts) {
			if(errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("재직상태를 입력해주세요.");
		}
		
		if (! isNotEmptyHireYear) {
			if(errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("연차를 입력해주세요.");
		}
		
		if (! isNotEmptyAddr) {
			if(errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("주소를 입력해주세요.");
		}
		
		if (! isNotEmptyBirth) {
			if(errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("생일을 입력해주세요.");
		}
		
		if (! isNotEmptyEmail) {
			if(errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("이메일을 입력해주세요.");
		}
		
		if (! isNotEmptyPwd) {
			if(errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("비밀번호를 입력해주세요.");
		}
		
//		if (! isNotEmptyConfirmPwd) {
//			if(errorMessage == null) {
//				errorMessage = new ArrayList<>();
//			}
//			errorMessage.add("비밀번호 확인을 입력해주세요.");
//		}
		
		if (! isNotEmptyMngrYn) {
			if(errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("임원여부를 선택해주세요.");
		}
		
		
		if (errorMessage != null) {
			return ApiResponse.BAD_REQUEST(errorMessage);
		}
		
		boolean isSuccess = this.employeeService.modifyOneEmployee(employeeVO);
		
		return ApiResponse.Ok(isSuccess);
	}
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
