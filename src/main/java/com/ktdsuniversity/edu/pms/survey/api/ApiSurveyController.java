package com.ktdsuniversity.edu.pms.survey.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.survey.service.SurveyQuestionPickService;
import com.ktdsuniversity.edu.pms.survey.service.SurveyQuestionService;
import com.ktdsuniversity.edu.pms.survey.service.SurveyReplyService;
import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyQuestionPickVO;
import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionPickVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;

@RestController
@RequestMapping("/api/survey")
public class ApiSurveyController {

	@Autowired
	private SurveyQuestionService surveyQuestionService;
	@Autowired
	private SurveyQuestionPickService surveyQuestionPickService;
	@Autowired
	private SurveyReplyService surveyReplyService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CommonCodeService commonCodeService;

	@GetMapping("/list")
	public ApiResponse viewSurveyListPage(SearchSurveyVO searchSurveyVO, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

		// 접속 유저에 따라 내려주는 데이터를 다르게 설정
		searchSurveyVO.setEmployeeVO(employeeVO);
		searchSurveyVO.setEmpId(employeeVO.getEmpId());

		// 관리자라면 프로젝트에 속하는 여부를 따지지 않고 다보여줌
		if (employeeVO.getAdmnCode().equals("301")) {
			SurveyListVO surveyListVO = this.surveyQuestionService.searchProject(searchSurveyVO);
			List<Object> dataList = new ArrayList<>();
			dataList.add(searchSurveyVO);
			dataList.add(surveyListVO);
			return ApiResponse.Ok(dataList);
		}

		// 설문조사 목록과 팀원 정보를 검색
		SurveyListVO teammate = this.surveyQuestionService.searchTeammate(searchSurveyVO);
		SurveyListVO surveyListVO = this.surveyQuestionService.searchProject(searchSurveyVO);

		// 공통 코드 목록을 가져옴
		List<CommonCodeVO> projectCommonCodeList = commonCodeService.getAllCommonCodeListByPId("400");

		// 로그인한 사용자가 PM인지 확인
		List<ProjectTeammateVO> tmList = this.projectService.getAllProjectTeammate().stream()
				.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId())).filter(tm -> tm.getRole().equals("PM"))
				.toList();
		boolean isPM = !tmList.isEmpty();

		// 로그인한 사용자가 참여한 프로젝트 목록을 가져옴
		List<ProjectTeammateVO> surveyYn = this.projectService.getAllProjectTeammate().stream()
				.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId())).toList();

		// 결과를 리스트에 담아 반환
		List<Object> dataList = new ArrayList<>();
		dataList.add(searchSurveyVO);
		dataList.add(surveyListVO);
//		dataList.add(teammate);
//		dataList.add(projectCommonCodeList);
		dataList.add(surveyYn);
		dataList.add(isPM);

		return ApiResponse.Ok(dataList);
	}

	@GetMapping("/view/{prjId}")
	public ApiResponse viewCompleteSurveyPage(@PathVariable String prjId) {
		SurveyQuestionVO surveyQuestion = this.surveyQuestionService.getOneSurveyForWrite(prjId);
		SurveyQuestionVO surveyQuestionVO = new SurveyQuestionVO();
		surveyQuestionVO.setPrjId(prjId);

		SurveyListVO questionList = this.surveyQuestionService.searchAllQuestions(surveyQuestionVO);
		List<SurveyQuestionPickVO> allPicks = new ArrayList<>();

		for (SurveyQuestionVO question : questionList.getQuestionList()) {
			SurveyQuestionPickVO surveyQuestionPickVO = new SurveyQuestionPickVO();
			surveyQuestionPickVO.setSrvId(question.getSrvId());
			SurveyListVO pickList = this.surveyQuestionPickService.searchAllPicks(surveyQuestionPickVO);
			allPicks.addAll(pickList.getPickList());
		}

		System.out.println("결과 아이디 보기" + prjId);
		int roleNoneCount = this.surveyQuestionService.getRoleNoneCount(prjId);
		int serveyDoneCount = this.surveyQuestionService.getServeyDoneCount(prjId);

		List<SurveyReplyVO> descriptiveTypeAnswer = this.surveyReplyService.getallDescriptiveTypeAnswer(prjId);

		List<Object> dataList = new ArrayList<>();
		dataList.add(surveyQuestion);
		dataList.add(questionList);
		dataList.add(allPicks);
		dataList.add(roleNoneCount);
		dataList.add(serveyDoneCount);
		dataList.add(descriptiveTypeAnswer);

		return ApiResponse.Ok(dataList);
	}

	// 지워도 될것 같음
	@GetMapping("/pick/{srvId}")
	public ApiResponse getAllPicksForWrite(@PathVariable String srvId, SurveyQuestionPickVO surveyQuestionPickVO) {
		surveyQuestionPickVO.setSrvId(srvId);

		List<SurveyQuestionPickVO> pickList = this.surveyQuestionPickService.getAllPicks(surveyQuestionPickVO);

		return ApiResponse.Ok(pickList);
	}

	@PostMapping("/write")
	public ApiResponse doSurveyWrite(@RequestParam String prjId) {
		SurveyQuestionVO surveyQuestionVO = this.surveyQuestionService.getOneSurveyForWrite(prjId);
		List<SurveyQuestionVO> questionList = this.surveyQuestionService.getAllQuestions().getQuestionList();
		List<SurveyQuestionPickVO> pickList = this.surveyQuestionPickService.getAllPicks().getPickList();

		List<Object> dataList = new ArrayList<>();
		dataList.add(surveyQuestionVO);
		dataList.add(questionList);
		dataList.add(pickList);
		return ApiResponse.Ok(dataList);
	}

	@GetMapping("/survey/create")
	public ApiResponse viewSurveyCreatePage(@RequestParam String prjId) {
		ProjectVO projectVO = this.projectService.getOneProject(prjId);
		SurveyQuestionVO surveyQuestionVO = this.surveyQuestionService.getOneSurvey(prjId);

		List<Object> dataList = new ArrayList<>();
		dataList.add(projectVO);
		dataList.add(surveyQuestionVO);
		return ApiResponse.Ok(dataList);
	}

	@PostMapping("/create/{prjId}")
	public ApiResponse doSurveyCreate(@PathVariable String prjId, Authentication authentication,
			@RequestBody List<SurveyQuestionVO> surveyQuestions) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

		List<SurveyQuestionVO> savedQuestions = new ArrayList<>();
		for (SurveyQuestionVO surveyQuestionVO : surveyQuestions) {
			surveyQuestionVO.setPrjId(prjId);
			surveyQuestionVO.setCrtrId(employeeVO.getEmpId());
			surveyQuestionService.createNewSurveyQuestion(surveyQuestionVO);
			savedQuestions.add(surveyQuestionVO);
		}

		return ApiResponse.Ok(savedQuestions);
	}

	@PostMapping("/answer/{srvId}")
	public ApiResponse doCreateNewAnswer(@PathVariable String srvId,
			@RequestBody List<SurveyQuestionPickVO> surveyQuestionPicks) {

		for (SurveyQuestionPickVO surveyQuestionPickVO : surveyQuestionPicks) {
			surveyQuestionPickVO.setSrvId(srvId);
			surveyQuestionPickService.createNewAnswer(surveyQuestionPickVO);
		}

		return ApiResponse.Ok(true);
	}

	@PostMapping("/reply/{srvId}")
	public ApiResponse ResponseSurvey(@PathVariable String srvId, Authentication authentication,
			@RequestBody SurveyReplyVO surveyReplyVO) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

		surveyReplyVO.setSrvId(srvId);
		surveyReplyVO.setCrtrId(employeeVO.getEmpId());
		SurveyQuestionVO surveyQuestionVO = this.surveyQuestionService.getOneProjectIdBySrvId(srvId);
		surveyReplyVO.setSurveyQuestionVO(surveyQuestionVO);
		System.out.println("선택지 ID :" + surveyReplyVO.getSqpId());
		boolean isSuccess = this.surveyReplyService.responseSurvey(surveyReplyVO);
		List<Object> dataList = new ArrayList<>();
		dataList.add(isSuccess);
		dataList.add(surveyReplyVO.getSrvId());
		dataList.add(employeeVO.getEmpId());
		// dataList.add(surveyReplyVO.getSrvId());
		// 응답 반환
		return ApiResponse.Ok(dataList);
	}

}
