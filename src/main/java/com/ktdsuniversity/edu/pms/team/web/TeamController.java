package com.ktdsuniversity.edu.pms.team.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.team.service.TeamService;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.StringUtil;

@Controller
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	@Autowired
	private DepartmentService departmentService;
	
	@ResponseBody
	@PostMapping("/ajax/team/create")
	public AjaxResponse doCreateNewTeam(TeamVO teamVO, Model model) {
//		PSH - 수정 : logger 변환
//		System.out.println(teamVO.getTmName());
		boolean isEmptyTeamName = StringUtil.isEmpty(teamVO.getTmName());
		boolean isEmptyTeamLeaderId= StringUtil.isEmpty(teamVO.getTmLeadId());
		boolean isEmptyChargeDept= StringUtil.isEmpty(teamVO.getDeptId());
		
		if (isEmptyTeamName) {
			model.addAttribute("errorMessage", "팀 이름은 필수 입력 값입니다.");
			model.addAttribute("teamVO", teamVO);
			return new AjaxResponse().append("errormessage", model);
		}
		if (isEmptyTeamLeaderId) {
			model.addAttribute("errorMessage", "팀장 이름은 필수 입력 값입니다.");
			model.addAttribute("teamVO", teamVO);
			return new AjaxResponse().append("errormessage", model);
		}
		if (isEmptyChargeDept) {
			model.addAttribute("errorMessage", "담당부서 아이디는 필수 입력 값입니다.");
			model.addAttribute("teamVO", teamVO);
			return new AjaxResponse().append("errormessage", model);
		}
		
		DepartmentListVO departmentListVo=this.departmentService.getDepartMent(teamVO.getTmLeadId());
		if(departmentListVo.getDepartmentList().size() > 0) {
			return new AjaxResponse().append("message", "이미 사용중인 아이디 입니다");
		}
		
		boolean isSuccess = this.teamService.createNewTeam(teamVO);
		return new AjaxResponse().append("result", isSuccess).append("nextUrl", "/department/search");
		
	}
	
	@ResponseBody
	@GetMapping("/ajax/team/emp")
	public AjaxResponse getEmpByDeptId(@RequestParam String deptId) {
		List<EmployeeVO> empList = this.departmentService.getEmpByDeptId(deptId);
		return new AjaxResponse().append("empList", empList);
	}
	
	@ResponseBody
	@GetMapping("/ajax/team/show")
	public AjaxResponse selectOptionShowTeam(@RequestParam String teamId) {
		TeamVO teamVO = this.teamService.selectOneTeam(teamId);
		List<EmployeeVO> empInTeam = this.teamService.getAllEmployeeInTeam(teamId);
		return new AjaxResponse().append("oneTeam", teamVO).append("empList", empInTeam);
	}
	
	@ResponseBody
	@PostMapping("/ajax/team/modify")
	public AjaxResponse modifyOneTeam(TeamVO teamVO) {
		
		DepartmentListVO departmentListVo=this.departmentService.getDepartMent(teamVO.getTmLeadId());
		if(departmentListVo.getDepartmentList().size() > 0) {
			return new AjaxResponse().append("message", "이미 사용중인 아이디 입니다");
		}
		
		boolean isModifySuccess = this.teamService.modifyOneTeam(teamVO);
		return new AjaxResponse().append("success", isModifySuccess).append("next", "/department/search");
		
	}
}















