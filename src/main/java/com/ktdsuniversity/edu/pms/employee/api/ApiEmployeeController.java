package com.ktdsuniversity.edu.pms.employee.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeChangeHistoryVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeDataVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeInfoVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;
import com.ktdsuniversity.edu.pms.employee.web.EmployeeController;
import com.ktdsuniversity.edu.pms.job.service.JobService;
import com.ktdsuniversity.edu.pms.job.vo.JobVO;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.team.service.TeamService;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.ValidationUtils;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

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
	@GetMapping("/employees")
	public ApiResponse getEmployeeList(SearchEmployeeVO searchEmployeeVO) {
		
		EmployeeListVO employeeListVO = this.employeeService.searchAllEmployee(searchEmployeeVO);
		
		return ApiResponse.Ok(employeeListVO.getEmployeeList(), employeeListVO.getEmployeeCnt(),
								searchEmployeeVO.getPageCount(), searchEmployeeVO.getPageNo() < searchEmployeeVO.getPageCount() -1);
	}
	
	@GetMapping("/employeeList")
	public ResponseEntity<List<EmployeeInfoVO>> getNewEmployeeList() {
		final var result = this.employeeService.getNewEmployeeList();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// 사원 상세 조회
	@GetMapping("/employee/view/{empId}")
	public ApiResponse getOneEmployee(@PathVariable("empId") String empId, Authentication authentication) {
		
		EmployeeVO employeeVO = this.employeeService.getOneEmployee(empId);
		
		// 변경이력 TODO@@!!
//		PaHistoryVO paHistoryVO = this.
//		List<JobVO> jobList = this.changeHistoryService.getAllJob();
//		List<CommonCodeVO> positionList = this.changeHistoryService.getAllPosition();
		
		return ApiResponse.Ok(employeeVO);
	}
	
	//일단 이거로 바꿈 
	@GetMapping("/employee/{empId}")
	public ApiResponse getEmployeeInfo(@PathVariable("empId") String empId, Authentication authentication) {
		EmployeeInfoVO employeeInfo = this.employeeService.getEmployeeInfo(empId);
		
		return ApiResponse.Ok(employeeInfo);
	}
	
	// 부서, 팀, 직무, 직급 리스트 조회
	@GetMapping("/employee/data")
	public ResponseEntity<Map<String, List<EmployeeDataVO>>> getDataList() {
		var result = new HashMap<String, List<EmployeeDataVO>>();
		List<EmployeeDataVO> deptList = this.employeeService.getDepartList();
		List<EmployeeDataVO> teamList = this.employeeService.getTeamList();
		List<EmployeeDataVO> jobList = this.employeeService.getJobList();
		List<EmployeeDataVO> positionList = this.employeeService.getEmployeeGradeList();
		List<EmployeeDataVO> workStsList = this.employeeService.getEmployeeWorkStsList();
		
		result.put("depart", deptList);
		result.put("team", teamList);
		result.put("job", jobList);
		result.put("position", positionList.stream().filter(grade -> {
            return Integer.parseInt(grade.getDataId()) <= 110;
    }).toList());
		result.put("workSts", workStsList.stream().filter(workSts -> {
			return Integer.parseInt(workSts.getDataId()) <= 204;
		}).toList());
		
		return new ResponseEntity<Map<String, List<EmployeeDataVO>>> (result, HttpStatus.OK);
	}
	
	
	// 수정
	@PutMapping("/employee/modify/{empId}")
	public ApiResponse domodifyEmployee(@RequestBody EmployeeVO employeeVO,
										@PathVariable("empId") String empId,
										Authentication authentication) {
		
		Validator<EmployeeVO> validator = new Validator<EmployeeVO>(employeeVO);
		
		validator.add("empName", Type.NOT_EMPTY, "이름을 입력해주세요.")
				.add("empId", Type.NOT_EMPTY, "사원번호를 입력해주세요.")
				.add("jobId", Type.NOT_EMPTY, "직무를 입력해주세요.")
				.add("deptId", Type.NOT_EMPTY, "부서를 입력해주세요.")
				.add("pstnId", Type.NOT_EMPTY, "직급을 입력해주세요.")
				.add("workSts", Type.NOT_EMPTY, "재직상태를 입력해주세요.")
				.add("salYear", Type.NOT_EMPTY, "호봉을 입력해주세요.")
				.add("hireDt", Type.NOT_EMPTY, "입사일을 설정해주세요.")
				.add("hireYear", Type.NOT_EMPTY, "연차를 설정해주세요.")
				.add("addr", Type.NOT_EMPTY, "주소를 입력해주세요.")
				.add("brth", Type.NOT_EMPTY, "생일을 입력해주세요.")
				.add("email", Type.NOT_EMPTY, "이메일을 입력해주세요.")
				.add("email", Type.EMAIL, "이메일 형식으로 입력해주세요.")
				.add("mngrYn", Type.NOT_EMPTY, "임원여부를 설정해주세요.")
				.start();
		
		if(validator.hasErrors()) {

			return ApiResponse.BAD_REQUEST(validator.getErrors());
		}
		
		EmployeeInfoVO prevData = this.employeeService.getEmployeeInfo(empId);
		
		EmployeeChangeHistoryVO changeData = new EmployeeChangeHistoryVO();
		changeData.setEmpId(empId);
		if(!prevData.getDeptId().equals(employeeVO.getDeptId())) {
			changeData.setType("depart");
			changeData.setPreValue(prevData.getDeptId());
			changeData.setCurValue(employeeVO.getDeptId());
			this.employeeService.insertEmployeeChangeHistory(changeData);
		} 

		if(!prevData.getJobId().equals(employeeVO.getJobId())) {
			changeData.setType("job");
			changeData.setPreValue(prevData.getJobId());
			changeData.setCurValue(employeeVO.getJobId());
			this.employeeService.insertEmployeeChangeHistory(changeData);
		}

		if(!prevData.getPstnId().equals(employeeVO.getPstnId())) {
			changeData.setType("position");
			changeData.setPreValue(prevData.getPstnId());
			changeData.setCurValue(employeeVO.getPstnId());
			this.employeeService.insertEmployeeChangeHistory(changeData);
		}

		
		boolean isSuccess = this.employeeService.modifyOneEmployee(employeeVO);
		
		return ApiResponse.Ok(isSuccess);
	}
	
	// 비밀번호 변경
	@PutMapping("/employee/modifyPwd/{empId}")
	public ApiResponse domodifyPwd(@RequestBody EmployeeInfoVO employeeInfoVO,
			@PathVariable("empId") String empId,
			Authentication authentication) {
		
		Validator<EmployeeInfoVO> validator = new Validator<EmployeeInfoVO>(employeeInfoVO);
		
		validator.add("pwd", Type.NOT_EMPTY, "비밀번호를 입력해주세요.")
				 .add("pwd", Type.NOT_EMPTY, "새로운 비밀번호를 입력해주세요.")
				 .add("pwd", Type.PASSWORD, "비밀번호 형식으로 입력해 주세요.")
				 .add("confirmPwd", Type.NOT_EMPTY, "비밀번호 확인을 입력해주세요.")
				 .add("confirmPwd", Type.EQUALS, employeeInfoVO.getPwd(), "동일한 비밀번호를 입력해 주세요.")
				 .start();
		
		if(validator.hasErrors()) {
			return ApiResponse.BAD_REQUEST(validator.getErrors());
		};
		
		boolean isSuccess = this.employeeService.modifyPwd(employeeInfoVO);
		
		return ApiResponse.Ok(isSuccess);
	}
	
	
	
	// 회원 등록
	@PostMapping("/employee")
	public ApiResponse registOneEmployee(@RequestBody EmployeeInfoVO employeeInfoVO,
											Authentication authentication) throws Exception {
		
		Validator<EmployeeInfoVO> validator = new Validator<>(employeeInfoVO);

		validator.add("empName", Type.NOT_EMPTY, "사원이름을 입력해 주세요.")
//				.add("pwd", Type.NOT_EMPTY, "비밀번호를 입력해 주세요.")
//				.add("pwd", Type.PASSWORD, "비밀번호 형식으로 입력해 주세요.")
//				.add("confirmPwd", Type.NOT_EMPTY, "비밀번호 확인을 입력해 주세요.")
//				.add("confirmPwd", Type.EQUALS, employeeInfoVO.getPwd(), "동일한 비밀번호를 입력해 주세요.")
				.add("hireDt", Type.NOT_EMPTY, "입사일을 지정해 주세요.")
//				.add("hireDt", Type.NOW_DATE, "입사일은 현재 날짜보다 이전이어야 합니다.")
				.add("addr", Type.NOT_EMPTY, "주소를 입력해 주세요.")
				.add("brth", Type.NOT_EMPTY, "생일을 지정해 주세요.")
				.add("brth", Type.NOW_DATE, "생일은 현재 날짜보다 이전이어야 합니다.")
				.start();
		if(validator.hasErrors()) {
			return ApiResponse.BAD_REQUEST(validator.getErrors());
		}
//		if (file != null) {
//			employeeVO.setFileName(file.getContentType());
//			validator.add("fileName", Type.IMAGE_FILE, "이미지 형식의 파일을 업로드 해주세요.");
//		}
		
		
		boolean isCreateSuccess = this.employeeService.createEmployee(employeeInfoVO);
		
		if(!isCreateSuccess) {
			throw new Exception("등록 실패");
		}
		
		return ApiResponse.Ok(isCreateSuccess);
	}
	
	
	// 프로필 사진 등록
    @PostMapping("/employee/profile/{empId}")
    public ApiResponse registProfileOneEmployee(@PathVariable String empId,
                                                @RequestParam(required = false) MultipartFile file,
                                                Authentication authentication) throws Exception {
        // VO 객체 생성 및 empId 설정
        EmployeeInfoVO employeeInfoVO = new EmployeeInfoVO();
        employeeInfoVO.setEmpId(empId);

        // 파일 존재 확인 및 설정
        if (file != null && !file.isEmpty()) {
            employeeInfoVO.setOriginFileName(file.getOriginalFilename());
            employeeInfoVO.setPrfl(file.getOriginalFilename());
        }

        // 프로필 생성 서비스 호출
        boolean isCreateSuccess = this.employeeService.createEmployeeProfile(employeeInfoVO, file);

        if (!isCreateSuccess) {
            throw new Exception("등록 실패");
        }

        return ApiResponse.Ok(isCreateSuccess);
    }
	
	@GetMapping("/employee/{empId}/history")
	public ResponseEntity<List<EmployeeChangeHistoryVO>> getEmployeeChangeHistoryList(@PathVariable("empId") String empId,
													Authentication authentication) throws Exception {
		
		List<EmployeeChangeHistoryVO> historyList = this.employeeService.getEmployeeChangeHistory(empId);
		
		return new ResponseEntity<>(historyList, HttpStatus.OK);
	}
	

	
}
