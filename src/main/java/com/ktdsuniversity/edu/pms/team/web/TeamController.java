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
import com.ktdsuniversity.edu.pms.team.service.TeamService;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.StringUtil;

@Controller
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private DepartmentService departmentservice;
	
	@ResponseBody
	@PostMapping("/ajax/team/create")
	public AjaxResponse doCreateNewTeam(TeamVO teamVO, Model model) {
		System.out.println(teamVO.getTmName());
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
		
		boolean isSuccess = this.teamService.createNewTeam(teamVO);
		return new AjaxResponse().append("result", isSuccess).append("nextUrl", "/department/search");
		
	}
	
	@ResponseBody
	@GetMapping("/ajax/team/show")
	public AjaxResponse selectOptionShowTeam(@RequestParam String teamId) {
		TeamVO teamVO = this.teamService.selectOneTeam(teamId);
		return new AjaxResponse().append("oneTeam", teamVO);
	}
	
	@ResponseBody
	@PostMapping("/ajax/team/modify")
	public AjaxResponse modifyOneTeam(TeamVO teamVO) {
		String str= this.departmentservice.getOnlypstnid(teamVO.getTmLeadId());
	    
	    if(str!=null) {
	    	int number = Integer.parseInt(str);
		    TeamListVO teamListVO = this.teamService.getaAllTeam();
		    List<TeamVO> teamList = teamListVO.getTeamList();
		    DepartmentListVO departmentListVO = this.departmentservice.getAllDepartment();
		    
		    for (TeamVO team : teamList) {
		        if (team.getTmLeadId().equals(teamVO.getTmLeadId())) {
		            return new AjaxResponse().append("message", "중복된 팀장 ID 값은 사용할 수 없습니다");
		        }
		        else if(number==101) {
		        	return new AjaxResponse().append("message", "인턴이상 사용이 가능 합니다");
		        }
		    }
		    
	        for (DepartmentVO dept : departmentListVO.getDepartmentList()) {
	            if (dept.getDeptLeadId().equals(teamVO.getTmLeadId())) {
	                return new AjaxResponse().append("message", "팀장 ID는 이미 다른 부서의 부서장 ID로 사용되고 있습니다");
	            }
	            else if(number==101) {
	            	 return new AjaxResponse().append("message", "인턴이상 사용이 가능 합니다");
		        }
	        }
	    }
	    else {
	    	 return new AjaxResponse().append("message", "아이디를 확인하세요");
	    }
		
		
		boolean isModifySuccess = this.teamService.modifyOneTeam(teamVO);
		return new AjaxResponse().append("success", isModifySuccess).append("next", "/department/search");
		
	}


}















