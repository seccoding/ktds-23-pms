package com.ktdsuniversity.edu.pms.team.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.team.service.TeamService;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.StringUtil;

@Controller
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
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

}















