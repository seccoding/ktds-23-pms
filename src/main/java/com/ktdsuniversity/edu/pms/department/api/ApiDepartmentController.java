package com.ktdsuniversity.edu.pms.department.api;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentApprovalVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.department.vo.SearchDepartmentVO;
import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.team.service.TeamService;
import com.ktdsuniversity.edu.pms.team.vo.TeamApprovalVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.ValidationUtils;

@RestController
@RequestMapping("/api/v1")
public class ApiDepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private EmployeeService employeeService;
	
	// 부서 리스트 가져오기
	@GetMapping("/department")
	public ApiResponse viewDepartmentListPage(SearchDepartmentVO searchDepartmentVO) {
		DepartmentListVO departmentListVO = this.departmentService.getAllDepartment();
		
		return ApiResponse.Ok(departmentListVO.getDepartmentList(), departmentListVO.getDepartmentCnt(),
				searchDepartmentVO.getPageCount(), searchDepartmentVO.getPageNo() < searchDepartmentVO.getPageCount() -1);
	}

	// 부서 상세정보 가져오기
	@GetMapping("/department/detail/{deptId}")
	public ApiResponse viewDepartmentDetail(@PathVariable String deptId) {
		DepartmentListVO departmentListVO = this.departmentService.getOnlyDepartment(deptId);
		
		return ApiResponse.Ok(departmentListVO.getDepartmentList());
	}
	
	// 부서의 팀 리스트 가져오기
	@GetMapping("/department/{deptId}")
	public ApiResponse viewteamListPage(@PathVariable String deptId) {
		TeamListVO teamListVO = this.teamService.getAllTeamList(deptId);
		
		return ApiResponse.Ok(teamListVO.getTeamList());
	}
	
	
	// 팀의 팀원 리스트 가져오기
	@GetMapping("/team/{teamId}")
	public ApiResponse selectOptionShowTeam(@PathVariable String teamId) {
		List<EmployeeVO> empInTeam = this.teamService.getAllEmployeeInTeam(teamId);
		
		return ApiResponse.Ok(empInTeam);
	}
	
	// 부서 등록 2단계 경영지원부 -> 대표이사
	@PostMapping("/department")
	public ApiResponse doCreateNewDepartment(@RequestBody DepartmentApprovalVO departmentApprovalVO, Authentication authentication) {
	
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		// update해야 하는 것!!
		// 1. 서비스에서 변경이력로그에 기록 남기기
		// 2. 부서장의 emp테이블 변경 및 트랜젝션..
		// 3. 주소록 검색..
		// 4. 여러부서를 등록할때 한번에 트랜젝션..... 으악!
		// 5. 이미 부서장으로 소속된 부서가 있는 부서장임명일 경우 validation... 해야됨 
		// 2,5 --> 사원에 먼저 채워주면 자연스럽게 트랜젝션까지 해결될듯?
		
		boolean isNotEmptyName = ValidationUtils.notEmpty(departmentApprovalVO.getDeptName());
		boolean isNotEmptyEmpName = ValidationUtils.notEmpty(departmentApprovalVO.getDeptLeadId());
		
		List<String> errorMessage = null;
		
		if (!isNotEmptyName) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("부서명을 입력해 주세요.");
		}
		
		if (!isNotEmptyEmpName) {
			if (errorMessage == null) {
				errorMessage = new ArrayList<>();
			}
			errorMessage.add("부서장을 입력해 주세요.");
		}
		
		if (errorMessage != null) {
			return ApiResponse.BAD_REQUEST(errorMessage);
		}
		
		
		departmentApprovalVO.setDeptApprReqtr(employeeVO.getEmpId());
		
		boolean isSuccess = this.departmentService.createNewDepartment(departmentApprovalVO);
		return  ApiResponse.Ok(isSuccess);
	}
	
	// 부서 삭제
	@DeleteMapping("/department/delete/{deptId}")
	public ApiResponse deleteOneDepartment(@PathVariable String deptId, Authentication authentication) {
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();

		// 삭제 요청자 삭제부서아이디 
		DepartmentApprovalVO departmentApprovalVO = new DepartmentApprovalVO();
		departmentApprovalVO.setDeptApprReqtr(employeeVO.getEmpId());
		departmentApprovalVO.setDeptId(deptId);

		boolean isSuccessDelete = this.departmentService.deleteOneDepartment(departmentApprovalVO);
		return  ApiResponse.Ok(isSuccessDelete); 
	}
	
	// 부서 수정
	@PutMapping("/department/modify")
	public ApiResponse modifyOneDepartment(@RequestBody DepartmentApprovalVO departmentApprovalVO, Authentication authentication) {
	
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		

		departmentApprovalVO.setDeptApprReqtr(employeeVO.getEmpId());

		boolean isModifySuccess = this.departmentService.modifyOneDepartment(departmentApprovalVO);
		return  ApiResponse.Ok(isModifySuccess);
	}

	// 팀등록 부서장 -> 경영지원부장 -> 대표이사
	@PostMapping("/team")
	public ApiResponse doCreateNewTeam(@RequestBody TeamApprovalVO teamApprovalVO, Authentication authentication) {
				
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		teamApprovalVO.setTmApprReqtr(employeeVO.getEmpId());
		
		boolean isSuccess = this.teamService.createNewTeam(teamApprovalVO);
		return  ApiResponse.Ok(isSuccess);
		
	}
	// 팀수정
	@PutMapping("/team/modify")
	public ApiResponse modifyOneTeam(@RequestBody TeamApprovalVO teamApprovalVO, Authentication authentication) {
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		teamApprovalVO.setTmApprReqtr(employeeVO.getEmpId());

		boolean isModifySuccess = this.teamService.modifyOneTeam(teamApprovalVO);
		return  ApiResponse.Ok(isModifySuccess);
		
	}
	
	// 팀삭제
	@DeleteMapping("/team/delete/{tmId}")
	public ApiResponse deleteOneTeam(@PathVariable String tmId,Authentication authentication) {

		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		// 삭제 요청자 삭제부서아이디 
		TeamApprovalVO teamApprovalVO = new TeamApprovalVO();
		teamApprovalVO.setTmApprReqtr(employeeVO.getEmpId());
		teamApprovalVO.setTmId(tmId);

		boolean isSuccessDelete = this.teamService.deleteOneTeam(teamApprovalVO);
		return  ApiResponse.Ok(isSuccessDelete);
	}

	// 팀 상세정보 조회
	@GetMapping("/team/detail/{tmId}")
	public ApiResponse viewTeamDetail(@PathVariable String tmId) {
		TeamVO TeamVO = this.teamService.selectOneTeam(tmId);
		
		return ApiResponse.Ok(TeamVO);
	}
	
	// 팀원 등록 팀장 -> 부서장 -> 경영지원부장
	@PostMapping("/team/member")
	public ApiResponse doCreateNewTeamMember(EmployeeVO employeeVO) {
		boolean isSuccess = this.teamService.createNewTeamMember(employeeVO);
		return ApiResponse.Ok(isSuccess);
	}
	
	
	
}
