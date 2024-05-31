package com.ktdsuniversity.edu.pms.project.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.SearchProjectVO;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;

@RestController
@RequestMapping("/api/project")
public class ApiProjectController {
	
	 @Autowired
	    private ProjectService projectService;

	    @Autowired
	    private CommonCodeService commonCodeService;

	    @Autowired
	    private DepartmentService departmentService;

	    @Autowired
	    private EmployeeService employeeService;

	    @Autowired
	    private RequirementService requirementService;
	    
	    // getAllProject + getAllProjectByProjectTeammateRole
	    @GetMapping("/search")
	    public ApiResponse viewSearchProjectListPage(SearchProjectVO searchProjectVO,Authentication authentication) {

	    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
	        // 접속 유저에 따라 내려주는 데이터를 다르게 설정
	        searchProjectVO.setEmployeeVO(employeeVO);

	        ProjectListVO projectListVO = projectService.searchProject(searchProjectVO);
	        List<CommonCodeVO> projectCommonCodeList = commonCodeService.getAllCommonCodeListByPId("400");
			
	        List<Object> dataList = new ArrayList<>();
			dataList.add(projectListVO);
			dataList.add(projectCommonCodeList);

	        return ApiResponse.Ok(dataList);
	    }

	    
	    @GetMapping("/view/{prjId}")
	    public ApiResponse viewProjectDetailPage(@PathVariable String prjId, Authentication authentication) {
	    	
	    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
			List<String> errorMessages = null;

	        ProjectVO projectVO = projectService.getOneProject(prjId);
	        int projectTeammateCount = projectService.getProjectTeammateCount(prjId);
	         // 요구사항 리스트 가져오기
	        List<RequirementVO> projectRequirementsList = requirementService.getAllRequirement(prjId).stream().
	                filter(requirement -> !requirement.getRqmSts().equals("605"))
	                .toList();
	        projectVO.setProjectRequirementsList(projectRequirementsList);
	        
	        // 사원 검증 로직, 관리자인지, 프로젝트의 팀에 해당되는 사람인지 확인해야한다. 권한 없으므로 예외
	        boolean isTeammate = projectVO.getProjectTeammateList().stream()
	                .anyMatch(teammate -> teammate.getTmId().equals(employeeVO.getEmpId()));
	        if (!employeeVO.getAdmnCode().equals("301")) {
	            if (!isTeammate) {
	                return ApiResponse.FORBIDDEN("접근 권한이 없습니다.");
	            }
	        }

	        // PM 뽑기
	        Optional<ProjectTeammateVO> pmOptional = projectVO.getProjectTeammateList().stream()
	                .filter(projectTeammateVO -> projectTeammateVO.getRole().equals("PM"))
	                .findFirst();
	        if (pmOptional.isPresent()) {
	        	return ApiResponse.Ok(projectVO, projectTeammateCount);
	        } else {
	        	errorMessages.add("존재하지 않는 프로젝트입니다.");
	        	return ApiResponse.BAD_REQUEST(errorMessages);
	        }

	        
	    }

}
