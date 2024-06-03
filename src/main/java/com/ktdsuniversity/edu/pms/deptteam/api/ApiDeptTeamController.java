//package com.ktdsuniversity.edu.pms.deptteam.api;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
//import com.ktdsuniversity.edu.pms.deptteam.service.DeptTeamService;
//import com.ktdsuniversity.edu.pms.deptteam.vo.SearchdepartmentVO;
//import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
//import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
//import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;
//import com.ktdsuniversity.edu.pms.utils.ApiResponse;
//
//@RestController
//@RequestMapping("/api/v1")
//public class ApiDeptTeamController {
//
//	@Autowired
//	private DeptTeamService deptTeamService;
//	
//	@Autowired
//	private EmployeeService employeeService;
//	
//	@GetMapping("/department")
//	public ApiResponse viewDepartmentListPage(SearchdepartmentVO searchdepartmentVO) {
//		DepartmentListVO departmentListVO = this.deptTeamService.getAllDepartment();
//		// 한 내용의 정보
//		DepartmentListVO getOnlyDepartmentListVO = this.deptTeamService.getOnlyDepartment();
//		TeamListVO getOnlyTeamListVO = this.deptTeamService.getOnlyTeam();
//		List<EmployeeVO> empList = this.employeeService.getCanBeDeptLead();
//
//
//		return ApiResponse.Ok(departmentListVO.getDepartmentList(), departmentListVO.getDepartmentCnt(),
//				searchdepartmentVO.getPageCount(), searchdepartmentVO.getPageNo() < searchdepartmentVO.getPageCount() -1);
//	}
//}
