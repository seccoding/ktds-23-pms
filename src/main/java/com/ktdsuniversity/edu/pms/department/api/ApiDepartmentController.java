package com.ktdsuniversity.edu.pms.department.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.department.vo.SearchDepartmentVO;
import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.team.service.TeamService;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;

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
	
	// 부서의 팀 리스트 가져오기
	@GetMapping("/department/{deptId}")
	public ApiResponse viewteamListPage(@PathVariable String deptId) {
		TeamListVO teamListVO = this.teamService.getAllTeamList(deptId);
		
		return ApiResponse.Ok(teamListVO.getTeamList());
	}
	
	// 팀의 팀원 리스트 가져오기
	@GetMapping("team/{teamId}")
	public ApiResponse selectOptionShowTeam(@PathVariable String teamId) {
		List<EmployeeVO> empInTeam = this.teamService.getAllEmployeeInTeam(teamId);
		
		return ApiResponse.Ok(empInTeam);
	}
	
//	@PostMapping("/ajax/department/create")
//	public ApiResponse doCreateNewDepartment(DepartmentVO departmentVO) {
//	
//		boolean isSuccess = this.departmentService.createNewDepartment(departmentVO);
//		return  ApiResponse.Ok(isSuccess);
//	}
//	
	
	
}
