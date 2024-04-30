package com.ktdsuniversity.edu.pms.main.web;


import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.borrow.service.BorrowService;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowListVO;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.service.LoginLogService;
import com.ktdsuniversity.edu.pms.memo.service.MemoService;
import com.ktdsuniversity.edu.pms.memo.vo.MemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private MemoService memoService;

    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private BorrowService borrowService;

    @GetMapping("/")
    public String viewMainLayoutPage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, Model model) {
        if (this.loginLogService.getPwdCndt(employeeVO.getEmpId()) > 0) {
            model.addAttribute("pwdMessage", "비밀번호를 변경한지 90일지 지났습니다. 비밀번호를 변경해주세요.");
        }
        return "layout/main_layout";
    }

    @GetMapping("/main/dashboard")
    public String viewMainDashboardPage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, SearchMemoVO searchMemoVO, Model model) {
        String deptName = this.departmentService.getDepartmentNameById((employeeVO.getDeptId()));
        model.addAttribute("deptname", deptName);

        List<ProjectVO> projectList;
        if (employeeVO.getAdmnCode().equals("301")) {
            projectList = projectService.getAllProject().getProjectList();
        } else {
            List<ProjectVO> allProjectByProjectTeammateId = projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId());
            projectList = allProjectByProjectTeammateId.stream().filter(project -> !project.getPrjSts().equals("409")).toList();
        }
        model.addAttribute("projects", projectList);
        
        List<BorrowVO> borrowList = this.borrowService.getUserRentalState(employeeVO.getEmpId());
        model.addAttribute("borrowList", borrowList);
        // 쪽지 필요 데이터 가져오기 (받은 쪽지 중 안 읽은 쪽지 갯수 및 목록정보) 
 		searchMemoVO.setEmpId(employeeVO.getEmpId());
 		MemoListVO memoListVO =this.memoService.getReceiveMemoReadYsearch(searchMemoVO);

 		model.addAttribute("memoList", memoListVO );
 		model.addAttribute("searchMemoVO", searchMemoVO);
     		

        return "main/dashboard";
    }

}
