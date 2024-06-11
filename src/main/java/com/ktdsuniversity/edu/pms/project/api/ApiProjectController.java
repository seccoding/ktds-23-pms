package com.ktdsuniversity.edu.pms.project.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.AccessDeniedException;
import com.ktdsuniversity.edu.pms.exceptions.CreationException;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.project.service.CalendarService;
import com.ktdsuniversity.edu.pms.project.service.ClientService;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.CalendarVO;
import com.ktdsuniversity.edu.pms.project.vo.ClientVO;
import com.ktdsuniversity.edu.pms.project.vo.CreateProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.SearchProjectVO;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

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
	    
	    @Autowired
	    private ClientService clientService;
	    
	    @Autowired
	    private CalendarService calendarService;
	    
	    // getAllProject + getAllProjectByProjectTeammateRole
	    @GetMapping("/search")
	    public ApiResponse getSearchProjectListPage(SearchProjectVO searchProjectVO,Authentication authentication) {

	    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
	        // 접속 유저에 따라 내려주는 데이터를 다르게 설정
	        searchProjectVO.setEmployeeVO(employeeVO);

	        ProjectListVO projectListVO = projectService.searchProject(searchProjectVO);
	        List<CommonCodeVO> projectCommonCodeList = commonCodeService.getAllCommonCodeListByPId("400");
		
	        List<ProjectTeammateVO> tmList = this.projectService.getAllProjectTeammate().stream()
					.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId())).filter(tm -> tm.getRole().equals("PM"))
					.toList();
			boolean isPM = !tmList.isEmpty();
	        
	        List<Object> dataList = new ArrayList<>();
			dataList.add(projectCommonCodeList);
			dataList.add(projectListVO);
			dataList.add(employeeVO);
			dataList.add(isPM);

	        return ApiResponse.Ok(dataList);
	    }

	    
	    @GetMapping("/view/{prjId}")
	    public ApiResponse getProjectDetailPage(@PathVariable String prjId, Authentication authentication) {
	    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
			List<String> errorMessages = new ArrayList<>();

	        ProjectVO projectVO = projectService.getOneProject(prjId);
	        int projectTeammateCount = projectService.getProjectTeammateCount(prjId);
	         // 요구사항 리스트 가져오기
	        List<RequirementVO> totalRequirementsList = requirementService.getAllRequirement(prjId).stream().toList();
	        List<RequirementVO> projectRequirementsList = requirementService.getAllRequirement(prjId).stream().
	        		filter(requirement -> !requirement.getRqmSts().equals("605"))
	        		.toList();
	        projectVO.setTotalRequireCnt(totalRequirementsList.size());
	        projectVO.setRequireCnt(projectRequirementsList.size());
	        
	        // client 가져오기
	        ClientVO client = this.clientService.getClientOfProject(projectVO.getClntInfo());
	        projectVO.setClientVO(client);
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
	        	ProjectTeammateVO pm = pmOptional.get();
	        	projectVO.setPm(pm);
	        	return ApiResponse.Ok(projectVO, projectTeammateCount);
	        } else {
	        	errorMessages.add("존재하지 않는 프로젝트입니다.");
	        	return ApiResponse.BAD_REQUEST(errorMessages);
	        }

	        
	    }
	    

	    /**
	     * 프로젝트 새로 생성하는 api
	     */
	    @PostMapping("/write")
	    public ApiResponse doWriteProject(@RequestBody CreateProjectVO createProjectVO, Authentication authentication) {
	    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
	        // 검증로직, 프로젝트 생성은 관리자만 가능하다.
	        if (!employeeVO.getAdmnCode().equals("301")) {
	        	return ApiResponse.FORBIDDEN("접근 권한이 없습니다.");
	        }
	        

//	        Validator<CreateProjectVO> validator = new Validator<>(createProjectVO);
//
//	        validator.add("prjName", Type.NOT_EMPTY, "프로젝트명을 입력해주세요.")
//	                .add("clntInfo", Type.NOT_EMPTY, "고객사를 입력해주세요.")
//	                .add("deptId", Type.NOT_EMPTY, "담당부서를 선택해주세요.")
//	                .add("strtDt", Type.NOT_EMPTY, "시작일을 입력해주세요.")
//	                .add("pmId", Type.NOT_EMPTY, "담당자를 입력해주세요.")
//	                .add("endDt", Type.NOT_EMPTY, "종료일을 입력해주세요.")
//	                .add("strtDt", Type.DATE, createProjectVO.getEndDt(), "종료일은 시작일보다 이후여야 합니다. 날짜를 다시 설정해주세요")
//	                .start();

//	        if (validator.hasErrors()) {
//	            Map<String, List<String>> errors = validator.getErrors();
////	            return ApiResponse.BAD_REQUEST(errorList);
//	            return null;
//	        }

	        // 2. 검증 로직에 잘 맞춰서 작성한 경우, 데이터 저장
	        // -> 세션에서 작성자 id 추출
	        createProjectVO.setCrtrId(employeeVO.getEmpId());

	        boolean isCreateSuccess = projectService.createNewProject(createProjectVO);

	        if (!isCreateSuccess) {
	            return ApiResponse.FORBIDDEN("프로젝트 생성 중 문제가 발생하였습니다.");
	        }
	        System.out.println("!!!!!!!!!!!!!"+isCreateSuccess);
	        return ApiResponse.Ok(isCreateSuccess);
	    }
	    
	    
	    
	    /**
	     * 프로젝트 수정 시 기존 데이터 보여주는 api
	     */
	    @GetMapping("/write/{prjId}")
	    public ApiResponse getProjectWritePage(@PathVariable String prjId, Authentication authentication) {
	    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
	        // 검증로직, 프로젝트 생성은 관리자만 가능하다.
	        if (!employeeVO.getAdmnCode().equals("301")) {
	        	return ApiResponse.FORBIDDEN("접근 권한이 없습니다.");
	        }
	        ProjectVO project = projectService.getOneProject(prjId);
	        Optional<ProjectTeammateVO> pmOptional = project.getProjectTeammateList().stream()
	                .filter(projectTeammateVO -> projectTeammateVO.getRole().equals("PM"))
	                .findFirst();
	        ProjectTeammateVO pm = pmOptional.get();
	        List<Object> dataList = new ArrayList<>();
	        List<EmployeeVO> employeeList = employeeService.getAllEmployee().getEmployeeList();
	        List<DepartmentVO> departmentList = departmentService.getOnlyDepartment().getDepartmentList();

	        dataList.add(employeeList);
	        dataList.add(departmentList);
	        dataList.add(pm);
	        return ApiResponse.Ok(dataList);
	    }
	    /**
	     * 프로젝트 수정 api
	     */
	    @PutMapping("/write/{prjId}")
	    public ApiResponse modifyProjectWritePage(@PathVariable String prjId, Authentication authentication,CreateProjectVO modifyProjectVO ) {
	    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    	EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
	    	 ProjectVO originalProjectVO = projectService.getOneProject(prjId);

	         if (originalProjectVO == null) {
	             return ApiResponse.FORBIDDEN("해당 프로젝트가 존재하지 않습니다.");
	         }

	         // 검증로직, 프로젝트 수정은 관리자만 가능하다.
	         if (!employeeVO.getAdmnCode().equals("301")) {
	        	 return ApiResponse.FORBIDDEN("접근 권한이 없습니다.");
	         }

	         // 3. 데이터 검증
	         Validator<CreateProjectVO> validator = new Validator<>(modifyProjectVO);

	         validator.add("prjName", Type.NOT_EMPTY, "프로젝트명을 입력해주세요.")
	                 .add("clntInfo", Type.NOT_EMPTY, "고객사를 입력해주세요.")
	                 .add("deptId", Type.NOT_EMPTY, "담당부서를 선택해주세요.")
	                 .add("strtDt", Type.NOT_EMPTY, "시작일을 선택해주세요.")
	                 .add("prjSts", Type.NOT_EMPTY, "상태코드를 선택해주세요.")
	                 .add("pmId", Type.NOT_EMPTY, "담당자를 선택해주세요.")
	                 .add("endDt", Type.NOT_EMPTY, "종료일을 선택해주세요.")
	                 .add("strtDt", Type.DATE, modifyProjectVO.getEndDt(), "종료일은 시작일보다 이후여야 합니다. 날짜를 다시 설정해주세요")
	                 .start();

	         if (validator.hasErrors()) {
	             Map<String, List<String>> errors = validator.getErrors();
	             return null;
	         }

	         // 4. 수정시작
	         boolean isUpdateSuccess = projectService.updateOneProject(modifyProjectVO);

	        
	         if (!isUpdateSuccess) {
	        	 return ApiResponse.FORBIDDEN("프로젝트 수정 중 문제가 발생하였습니다.");
	         }
	         return ApiResponse.Ok(isUpdateSuccess);
	    }
	    
	    @GetMapping("/client")
	    public ApiResponse getClient(Authentication authentication) {
	    	List<ClientVO> clientList = this.clientService.getAllClient();
	    	return ApiResponse.Ok(clientList);
	    }
	    
	    @PostMapping("/client")
	    public ApiResponse doCreateClient(ClientVO clientVO) {
	    	boolean isSuccess = this.clientService.createClient(clientVO);
	    	return ApiResponse.Ok(isSuccess);
	    }
	    
	    @GetMapping("/employee/findbydeptid/{deptId}")
		public ApiResponse findEmployeesByDeptId(@PathVariable String deptId, Authentication authentication) {
			List<EmployeeVO> employeeListVO = this.employeeService.findEmployeesByDeptId(deptId);
			for(EmployeeVO emp:employeeListVO) {
				emp = this.employeeService.getOneEmployee(emp.getEmpId());
			}
			return ApiResponse.Ok(employeeListVO);
		}
	    
	    @GetMapping("/calendar/{prjId}")
	    public ApiResponse getCalendar(@PathVariable String prjId,Authentication authentication) {
	    	List<CalendarVO> calendarList = this.calendarService.getCalendarByPrjId(prjId);
	    	return ApiResponse.Ok(calendarList);
	    }
	    
	    
	    @PostMapping("/calendar")
	    public ApiResponse addCalendar(@RequestBody CalendarVO calendarVO, Authentication authentication) {
	    	
	    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
	        // 검증로직, 프로젝트 생성은 관리자만 가능하다.
	        if (!employeeVO.getAdmnCode().equals("301")) {
	        	return ApiResponse.FORBIDDEN("접근 권한이 없습니다.");
	        }
	    	boolean isSuccess = this.calendarService.addCalendar(calendarVO);
	    	if(!isSuccess) {
	    		return ApiResponse.FORBIDDEN("캘린더에 추가하지 못했습니다.");
	    	}
	    	return ApiResponse.Ok(isSuccess);
	    }
	    
	    @PutMapping("/calendar")
	    public ApiResponse modifyCalendar(@RequestBody CalendarVO calendarVO, Authentication authentication) {
	    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
	        // 검증로직, 프로젝트 생성은 관리자만 가능하다.
	        if (!employeeVO.getAdmnCode().equals("301")) {
	        	return ApiResponse.FORBIDDEN("접근 권한이 없습니다.");
	        }
	    	boolean isSuccess = this.calendarService.modifyCalendar(calendarVO);
	    	if(!isSuccess) {
	    		return ApiResponse.FORBIDDEN("수정 중 오류가 발생했습니다.");
	    	}
	    	return ApiResponse.Ok(isSuccess);
	    }

}
