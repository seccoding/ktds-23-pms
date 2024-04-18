package com.ktdsuniversity.edu.pms.project.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
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
import org.springframework.web.bind.annotation.Mapping;
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

    // 세션에 따라서 보여줘야할 프로젝트 리스트를 바꿔야함
    // getAllProject + getAllProjectByProjectTeammateRole
    @GetMapping("/project/search")
    public String viewSearchProjectListPage(Model model,
                                            SearchProjectVO searchProjectVO) {
//        ProjectListVO projectListVO = projectService.getAllProject();

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
    public String viewProjectDetailPage(@RequestParam String prjId, Model model) {
        ProjectVO projectVO = projectService.getOneProject(prjId);
        int projectTeammateCount = projectService.getProjectTeammateCount(prjId);
        List<RequirementVO> projectRequirementsList = requirementService.getAllRequirement(prjId);

        // 사원 검증 로직, 관리자인지, 프로젝트의 팀에 해당되는 사람인지 확인해야한다. 권한 없으므로 예외
        // boolean isTeammate = projectVO.getProjectTeammateList().stream()
        // .anyMatch(teammate -> teammate.getTmId().equals(세션에 있는 사원 아이디));

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

    // Use Session, user 가 해당 프로젝트에 속해있는지를 검증해야함.
    @GetMapping("/project/team")
    public String viewProjectTeamPage(@RequestParam String prjId, Model model) {
        String prjName = projectService.getOneProject(prjId).getPrjName();
        int teammateCount = projectService.getProjectTeammateCount(prjId);
        List<ProjectTeammateVO> teammate = projectService.getAllProjectTeammateByProjectId(prjId);

        model.addAttribute("projectName", prjName);
        model.addAttribute("teammateCount", teammateCount);
        model.addAttribute("teammate", teammate);

        return "project/teammate";
    }


    @GetMapping("/project/write")
    public String viewProjectWritePage(Model model) {
        // 검증로직 추가 필요
        List<EmployeeVO> employeeList = employeeService.getAllEmployee().getEmployeeList();
        List<DepartmentVO> departmentList = departmentService.getOnlyDepartment().getDepartmentList();

        model.addAttribute("employee", employeeList);
        model.addAttribute("department", departmentList);

        return "project/projectwrite";
    }

    // 작성자 추가를 위해 SessionAttribute 추가 필요, @SessionAttribute("_LOGIN_USER_")
    // MemberVO
    // memberVO
    // form action 추가 필요
    @ResponseBody
    @PostMapping("/ajax/project/write")
    public AjaxResponse writeProject(CreateProjectVO createProjectVO) {
        // 0. session memberVO가 admin 이 아닌 경우, return list page or return 400
        // page(잘못된
        // 접근)

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

        if (createProjectVO.getReqYn() == null) {
            if (createProjectVO.getIsYn() != null
                    || createProjectVO.getKnlYn() != null
                    || createProjectVO.getQaYn() != null) {
                throw new CreationException();
            }
        }

        // 2. 검증 로직에 잘 맞춰서 작성한 경우, 데이터 저장
        // 2-1. 세션에서 작성자 id 추출, projectVO.setCrtrId();
        // 현재는 정적 데이터로 해결함.
        createProjectVO.setCrtrId("system01");

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
                                        Model model) {

        ProjectVO projectVO = projectService.getOneProject(prjId);
        List<DepartmentVO> departmentList = departmentService.getOnlyDepartment().getDepartmentList();
        List<EmployeeVO> employeeList = employeeService.getAllEmployee().getEmployeeList();
        List<CommonCodeVO> projectCommonCodeList = commonCodeService.getAllCommonCodeListByPId("400");
        // 작성자 또는 PM인지를 검증하는 로직 작성 필요

        // PM 뽑기
        Optional<ProjectTeammateVO> pmOptional = projectVO.getProjectTeammateList().stream()
                .filter(projectTeammateVO -> projectTeammateVO.getRole().equals("PM"))
                .findFirst();

        if (pmOptional.isPresent()) {
            ProjectTeammateVO pm = pmOptional.get();
            model.addAttribute("project", projectVO);
            model.addAttribute("pm", pm);
            model.addAttribute("department", departmentList);
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
    public AjaxResponse modifyProject(@PathVariable String prjId, CreateProjectVO modifyProjectVO) {
        // 1. 프로젝트를 가져와서 있는지 확인, 세션 검증용
        ProjectVO originalProjectVO = projectService.getOneProject(prjId);
        // 2. 세션으로 관리자 판별 (originalProjectVO와 유저를 판별 및 유저 권한으로 판별), 실패 시 throw
        // new
        // RuntimeException

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

        if (modifyProjectVO.getReqYn() == null) {
            if (modifyProjectVO.getIsYn() != null
                    || modifyProjectVO.getKnlYn() != null
                    || modifyProjectVO.getQaYn() != null) {
                throw new CreationException();
            }
        }

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
    public String deleteProject(@PathVariable String projectId) {
        // 1. 프로젝트를 가져와서 있는지 확인
        ProjectVO originalProjectVO = projectService.getOneProject(projectId);

        // 2. 검증 로직 (originalProjectVO와 유저를 판별 및 유저 권한으로 판별), 실패 시 throw new
        // 작성자 또는 관리자
        // RuntimeException

        // 3. 데이터 삭제 여부 확인
        boolean isDeleteSuccess = projectService.deleteOneProject(projectId);

        if (isDeleteSuccess) {
            return "redirect:/project/search";
//            성공로그
        } else {
//            실패로그
            return "redirect:/project/view?prjId=" + projectId;
        }
    }

    // 세션 추가 필요
    @ResponseBody
    @PostMapping("/ajax/teammate/delete/massive")
    public AjaxResponse doDeleteMassiveTeammate(@RequestParam("deleteItems[]") List<String> deleteItems) {
       boolean deleteResult = projectService.deleteManyTeammate(deleteItems);

        return new AjaxResponse().append("result", deleteResult);
    }

    @ResponseBody
    @GetMapping("/ajax/teammate/delete/{prjTmId}")
    public AjaxResponse doDeleteTeammate(@PathVariable String prjTmId) {
        boolean deleteResult = projectService.deleteOneTeammate(prjTmId);

        return new AjaxResponse().append("result", deleteResult);
    }
}
