package com.ktdsuniversity.edu.pms.project.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.AccessDeniedException;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.exceptions.CreationException;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.CreateProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.SearchProjectVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * TODO
 */
// 수정자 추가를 위해 SessionAttribute 추가 필요,
// 수정자 추가 시, Mapper 에도 컬럼 추가 필요 Parameter 도 id 에서 VO로 변경 필요
@Controller
public class ProjectController {

    Logger logger = LoggerFactory.getLogger(ProjectController.class);

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
    @GetMapping("/project/search")
    public String viewSearchProjectListPage(Model model,
                                            SearchProjectVO searchProjectVO,
                                            @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

        // 접속 유저에 따라 내려주는 데이터를 다르게 설정
        searchProjectVO.setEmployeeVO(employeeVO);

        ProjectListVO projectListVO = projectService
                .searchProject(searchProjectVO);
        List<CommonCodeVO> projectCommonCodeList = commonCodeService
                .getAllCommonCodeListByPId("400");

        model.addAttribute("commonCodeList", projectCommonCodeList);
        model.addAttribute("projectList", projectListVO);
        model.addAttribute("searchProjectVO", searchProjectVO);

        return "project/projectlist";
    }

    @GetMapping("/project/view")
    public String viewProjectDetailPage(@RequestParam String prjId,
                                        @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
                                        Model model) {

        ProjectVO projectVO = projectService.getOneProject(prjId);
        int projectTeammateCount = projectService.getProjectTeammateCount(prjId);
        List<RequirementVO> projectRequirementsList = requirementService.getAllRequirement(prjId).stream().
                filter(requirement -> !requirement.getRqmSts().equals("605"))
                .toList();

        // 사원 검증 로직, 관리자인지, 프로젝트의 팀에 해당되는 사람인지 확인해야한다. 권한 없으므로 예외
        boolean isTeammate = projectVO.getProjectTeammateList().stream()
                .anyMatch(teammate -> teammate.getTmId().equals(employeeVO.getEmpId()));

        if (!employeeVO.getAdmnCode().equals("301")) {
            if (!isTeammate) {
                throw new AccessDeniedException();
            }
        }

        // PM 뽑기
        Optional<ProjectTeammateVO> pmOptional = projectVO.getProjectTeammateList().stream()
                .filter(projectTeammateVO -> projectTeammateVO.getRole().equals("PM"))
                .findFirst();

        if (pmOptional.isPresent()) {
            ProjectTeammateVO pm = pmOptional.get();
            model.addAttribute("project", projectVO);
            model.addAttribute("teammateCount", projectTeammateCount);
            model.addAttribute("pm", pm);
            model.addAttribute("requirement", projectRequirementsList);
        } else {
            throw new PageNotFoundException();
        }

        return "project/projectview";
    }

    // chart.js api data
    @ResponseBody
    @GetMapping("/ajax/project/status/{projectId}")
    public AjaxResponse responseProjectStatus(@PathVariable String projectId) {
        return new AjaxResponse().append("chartData", projectService.getProjectStatus(projectId));
    }

    @GetMapping("/project/team")
    public String viewProjectTeamPage(@RequestParam String prjId,
                                      Model model,
                                      @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {


        ProjectVO project = projectService.getOneProject(prjId);
        List<ProjectTeammateVO> projectTeammateList = project.getProjectTeammateList();
        int teammateCount = projectService.getProjectTeammateCount(prjId);
        List<ProjectTeammateVO> teammate = projectService.getAllProjectTeammateByProjectId(prjId);

        // 사원 검증 로직, 관리자인지, 프로젝트의 팀에 해당되는 사람인지 확인해야한다. 권한 없으므로 예외
        boolean isTeammate = projectTeammateList.stream()
                .anyMatch(tm -> tm.getTmId().equals(employeeVO.getEmpId()));

        if (!employeeVO.getAdmnCode().equals("301")) {
            if (!isTeammate) {
                throw new AccessDeniedException();
            }
        }

        Optional<ProjectTeammateVO> pmOptional = projectTeammateList.stream()
                .filter(projectTeammateVO -> projectTeammateVO.getRole().equals("PM"))
                .findFirst();

        if (pmOptional.isPresent()) {
            ProjectTeammateVO pm = pmOptional.get();
            model.addAttribute("deptId", project.getDeptId());
            model.addAttribute("project", project);
            model.addAttribute("teammateCount", teammateCount);
            model.addAttribute("teammate", teammate);
            model.addAttribute("pm", pm);
        } else {
            throw new PageNotFoundException();
        }

        return "project/teammate";
    }

//    @GetMapping("/project/write")
//    public String viewProjectWritePage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
//                                       Model model) {
//        // 검증로직, 프로젝트 생성은 관리자만 가능하다.
//        if (!employeeVO.getAdmnCode().equals("301")) {
//            throw new AccessDeniedException();
//        }
//
//        List<EmployeeVO> employeeList = employeeService.getAllEmployee().getEmployeeList();
//        List<DepartmentVO> departmentList = departmentService.getOnlyDepartment().getDepartmentList();
//
//        model.addAttribute("employee", employeeList);
//        model.addAttribute("department", departmentList);
//
//        return "project/projectwrite";
//    }

    @ResponseBody
    @PostMapping("/ajax/project/write")
    public AjaxResponse writeProject(CreateProjectVO createProjectVO,
                                     @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
        // 검증로직, 프로젝트 생성은 관리자만 가능하다.
        if (!employeeVO.getAdmnCode().equals("301")) {
            throw new AccessDeniedException();
        }

        Validator<CreateProjectVO> validator = new Validator<>(createProjectVO);

        validator.add("prjName", Type.NOT_EMPTY, "프로젝트명을 입력해주세요.")
                .add("clntInfo", Type.NOT_EMPTY, "고객사를 입력해주세요.")
                .add("deptId", Type.NOT_EMPTY, "담당부서를 선택해주세요.")
                .add("strtDt", Type.NOT_EMPTY, "시작일을 입력해주세요.")
                .add("pmId", Type.NOT_EMPTY, "담당자를 입력해주세요.")
                .add("endDt", Type.NOT_EMPTY, "종료일을 입력해주세요.")
                .add("strtDt", Type.DATE, createProjectVO.getEndDt(), "종료일은 시작일보다 이후여야 합니다. 날짜를 다시 설정해주세요")
                .start();

        if (validator.hasErrors()) {
            Map<String, List<String>> errors = validator.getErrors();
            return new AjaxResponse().append("errors", errors);
        }

//        if (createProjectVO.getReqYn() == null) {
//            if (createProjectVO.getIsYn() != null
//                    || createProjectVO.getKnlYn() != null
//                    || createProjectVO.getQaYn() != null) {
//                throw new CreationException();
//            }
//        }

        // 2. 검증 로직에 잘 맞춰서 작성한 경우, 데이터 저장
        // -> 세션에서 작성자 id 추출
        createProjectVO.setCrtrId(employeeVO.getEmpId());

        boolean isCreateSuccess = projectService
                .createNewProject(createProjectVO);

        if (!isCreateSuccess) {
            throw new CreationException();
        }

        String prjId = createProjectVO.getPrjId();

        return new AjaxResponse().append("next", "/project/view?prjId=" + prjId);
    }

    @GetMapping("/project/modify/{prjId}")
    public String viewProjectModifyPage(@PathVariable String prjId,
                                        Model model,
                                        @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
        // 검증로직, 프로젝트 수정은 관리자만 가능하다.
        if (!employeeVO.getAdmnCode().equals("301")) {
            throw new AccessDeniedException();
        }


        ProjectVO projectVO = projectService.getOneProject(prjId);
//        List<DepartmentVO> departmentList = departmentService.getOnlyDepartment().getDepartmentList();
        List<EmployeeVO> employeeList = employeeService.getAllEmployee().getEmployeeList();
        List<CommonCodeVO> projectCommonCodeList = commonCodeService.getAllCommonCodeListByPId("400");

        // PM 뽑기
        Optional<ProjectTeammateVO> pmOptional = projectVO.getProjectTeammateList().stream()
                .filter(projectTeammateVO -> projectTeammateVO.getRole().equals("PM"))
                .findFirst();

        if (pmOptional.isPresent()) {
            ProjectTeammateVO pm = pmOptional.get();
            model.addAttribute("project", projectVO);
            model.addAttribute("pm", pm);
//            model.addAttribute("department", departmentList);
            model.addAttribute("employee", employeeList);
            model.addAttribute("commonCodeList", projectCommonCodeList);
        } else {
            throw new PageNotFoundException();
        }

        return "project/projectmodify";
    }

    // Use Session
    // 수정자 추가를 위해 SessionAttribute 추가 필요
    @ResponseBody
    @PostMapping("/ajax/project/modify/{prjId}")
    public AjaxResponse modifyProject(@PathVariable String prjId,
                                      CreateProjectVO modifyProjectVO,
                                      @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
        // 1. 프로젝트를 가져와서 있는지 확인, 세션 검증용
        ProjectVO originalProjectVO = projectService.getOneProject(prjId);

        if (originalProjectVO == null) {
            throw new PageNotFoundException();
        }

        // 검증로직, 프로젝트 수정은 관리자만 가능하다.
        if (!employeeVO.getAdmnCode().equals("301")) {
            throw new AccessDeniedException();
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
            return new AjaxResponse().append("errors", errors);
        }

//        if (modifyProjectVO.getReqYn() == null) {
//            if (modifyProjectVO.getIsYn() != null
//                    || modifyProjectVO.getKnlYn() != null
//                    || modifyProjectVO.getQaYn() != null) {
//                throw new CreationException();
//            }
//        }

        // 4. 수정시작
        boolean isUpdateSuccess = projectService.updateOneProject(modifyProjectVO);

        if (isUpdateSuccess) {
            return new AjaxResponse().append("next", "/project/view?prjId=" + prjId);
        } else {
            throw new IllegalArgumentException();
        }
    }

    // Use Session
    @GetMapping("/project/delete/{projectId}")
    public String deleteProject(@PathVariable String projectId,
                                @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
        // 1. 프로젝트를 가져와서 있는지 확인
        ProjectVO originalProjectVO = projectService.getOneProject(projectId);

        if (originalProjectVO == null) {
            throw new PageNotFoundException();
        }

        // 2. 검증 로직 (originalProjectVO와 유저를 판별 및 유저 권한으로 판별), 실패 시 throw new
        // 검증로직, 프로젝트 삭제는 관리자만 가능하다.
        if (!employeeVO.getAdmnCode().equals("301")) {
            throw new AccessDeniedException();
        }

        // 3. 데이터 삭제 여부 확인
        boolean isDeleteSuccess = projectService.deleteOneProject(projectId);

        if (isDeleteSuccess) {
            return "redirect:/project/search";
        } else {
            return "redirect:/project/view?prjId=" + projectId;
        }
    }

    // 세션 추가 필요, PM, ADMIN
    @ResponseBody
    @PostMapping("/ajax/teammate/delete/massive")
    public AjaxResponse deleteMassiveTeammate(@RequestParam("deleteItems[]") List<String> deleteItems,
                                              @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
        boolean deleteResult = projectService.deleteManyTeammate(deleteItems);

        return new AjaxResponse().append("result", deleteResult);
    }

    @ResponseBody
    @GetMapping("/ajax/teammate/delete/{prjTmId}")
    public AjaxResponse deleteTeammate(@PathVariable String prjTmId,
                                       @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
        boolean deleteResult = projectService.deleteOneTeammate(prjTmId);

        return new AjaxResponse().append("result", deleteResult);
    }

    //TODO
    // 이거 막는거 좀 고민해야함..
    @ResponseBody
    @GetMapping("/ajax/department-teammate/{deptId}")
    public AjaxResponse viewDepartmentTeammate(@PathVariable String deptId,
                                               @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
        List<EmployeeVO> allEmployeeList = employeeService.getAllEmployee().getEmployeeList();

        List<EmployeeVO> list = allEmployeeList.stream()
                .filter(emp -> emp.getDeptId().equals(deptId))
                .toList();

        return new AjaxResponse().append("teammateList", list);
    }

    @ResponseBody
    @PostMapping("/ajax/teammate/add")
    public AjaxResponse addNewProjectTeammate(ProjectTeammateVO newProjectTeammate,
                                              @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
        ProjectVO projectVO = projectService.getOneProject(newProjectTeammate.getPrjId());

        if (projectVO == null) {
            throw new PageNotFoundException();
        }

        // PM 뽑기
        Optional<ProjectTeammateVO> pmOptional = projectVO.getProjectTeammateList().stream()
                .filter(projectTeammateVO -> projectTeammateVO.getRole().equals("PM"))
                .findFirst();

        if (pmOptional.isPresent()) {
            ProjectTeammateVO pm = pmOptional.get();
            if (!employeeVO.getEmpId().equals(pm.getTmId())) {
                if (!employeeVO.getAdmnCode().equals("301")) {
                    throw new AccessDeniedException();
                }
            }
        }

        boolean addResult = projectService.insertOneTeammate(newProjectTeammate);

        return new AjaxResponse().append("result", addResult).append("message", "팀원을 등록할 수 없습니다.");
    }
}
