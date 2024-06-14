package com.ktdsuniversity.edu.pms.qna.api;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.AccessDeniedException;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.qna.service.QnaService;
import com.ktdsuniversity.edu.pms.qna.vo.QnaListVO;
import com.ktdsuniversity.edu.pms.qna.vo.QnaRecommendVO;
import com.ktdsuniversity.edu.pms.qna.vo.QnaVO;
import com.ktdsuniversity.edu.pms.qna.vo.SearchQnaVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

@RestController
@RequestMapping("/api/v1")
public class ApiQnaController {
	Logger logger = LoggerFactory.getLogger(ApiQnaController.class);
	
	@Autowired
	private FileHandler fileHandler;
	
	@Autowired
	private QnaService qnaService;
	
	@Autowired
	private ProjectService projectService;
	

	// 전체 리스트 조회
	@GetMapping("/qna")
	public ApiResponse viewQnaListPage(Authentication authentication, SearchQnaVO searchQnaVO,
									   @RequestParam(required = false) String qaId) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		ProjectListVO projectList = this.projectService.getAllProject();
		if(!employeeVO.getAdmnCode().equals("301")) {
			searchQnaVO.setEmpId(employeeVO.getEmpId());
			projectList.setProjectList(this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId()));
		} else {}
		
		projectList.setProjectList(
					projectList.getProjectList().stream().toList());
		
		QnaListVO qnaList = this.qnaService.searchAllQna(searchQnaVO);
		return ApiResponse.Ok(qnaList.getQnaList(), qnaList.getQnaCnt(), 
							  searchQnaVO.getPageCount(), searchQnaVO.getPageNo() < searchQnaVO.getPageCount() - 1);
		
	}
	
	// 게시글 상세 조회 
	@GetMapping("/qna/view")
	public ApiResponse viewDetailQnaListPage(@RequestParam String qaId, 
											 Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		QnaVO qnaVO = this.qnaService.getOneQna(qaId, true);
		
		return ApiResponse.Ok(qnaVO);
		
	}
	
	// 글 작성 페이지 
	@GetMapping("/qna/write")
	public ApiResponse viewQnaWrite(Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		List<ProjectVO> projectList = this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId());
		projectList.stream().toList();
		return ApiResponse.Ok(projectList);
		
	}
	
	//작성
	@PostMapping("/qna/write")
	public ApiResponse qnaWrite(QnaVO qnaVO, 
								@RequestParam(value="file", required=false)MultipartFile file,
								Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		Map<String, List<String>> error = this.qnaValidator(qnaVO);
		if(error != null) {
			return ApiResponse.Ok(error);
		}
		// 유저 검증 
		qnaVO.setCrtrId(employeeVO.getEmpId());
		if(!qnaVO.getCrtrId().equals(employeeVO.getEmpId()) && employeeVO.getMngrYn().equals("N")) {
			throw new AccessDeniedException();
		}
		boolean isCreateSuccess = this.qnaService.createNewQna(qnaVO, file);
		if (isCreateSuccess) {
			logger.info("글 등록이 완료되었습니다.");
		} else {
			logger.info("글 등록이 실패했습니다.");
		}
		String qaId = qnaVO.getQaId();
		return ApiResponse.Ok(isCreateSuccess);
	}
	
	// 1사원 1추천 
	@PostMapping("/qna/recommend/{pPostId}")
	public ApiResponse getRecommendQna(@PathVariable String pPostId,
										Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		QnaRecommendVO qnaRecommendVO = new QnaRecommendVO();
		
		qnaRecommendVO.setpPostId(pPostId);
		qnaRecommendVO.setCrtrId(employeeVO.getEmpId());
		
		boolean isRecommend = qnaService.updateRecommend(qnaRecommendVO);
		int recResult = qnaService.getQnaRecommendCount(pPostId);
		
		// 추천 유무 확인 
		if (isRecommend) {
			return ApiResponse.Ok("추천이 완료되었습니다.", recResult);
		} else {
			return ApiResponse.Ok("이미 추천했습니다.", recResult);
		}
	}
	
	// 수정 페이지 
	@GetMapping("/qna/modify/{qaId}")
	public ApiResponse viewQnaModify(@PathVariable String qaId, 
									 Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		QnaVO qnaVO = this.qnaService.getOneQna(qaId, false);
		
		//검증
		if(!employeeVO.getEmpId().equals(qnaVO.getCrtrId()) 
				&& employeeVO.getMngrYn().equals("N")) {
			throw new AccessDeniedException();
		}
		return ApiResponse.Ok(qnaVO);
	}
	
	@PostMapping("/qna/modify")
	public ApiResponse qnaModify(String qaId, MultipartFile file, QnaVO qnaVO,
								 Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		QnaVO originqnaVO = this.qnaService.getOneQna(qaId, false);
		
		//검증 
		if(!originqnaVO.getCrtrId().equals(employeeVO.getEmpId()) 
				&& employeeVO.getMngrYn().equals("N")) {
			throw new PageNotFoundException();
		}
		Map<String, List<String>> error = this.qnaValidator(qnaVO);
		if(error != null) {
			return ApiResponse.Ok(error);
		}
		qnaVO.setQaId(qaId);
		qnaVO.setCrtrId(employeeVO.getEmpId());
		
		boolean isUpdateSuccess = this.qnaService.updateOneQna(qnaVO, file);
		
		if(isUpdateSuccess) {
			logger.info("수정이 완료되었습니다.");
		} else {
			logger.info("수정이 실패했습니다.");
		}
		
		return ApiResponse.Ok(isUpdateSuccess);
	}
	
	@GetMapping("/qna/delete/{qaId}")
	public ApiResponse qnaDelete(@PathVariable String qaId, Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		QnaVO originQnaVO = this.qnaService.getOneQna(qaId, false);
		
		//검증 
		if(!originQnaVO.getCrtrId().equals(employeeVO.getEmpId())
				&& employeeVO.getMngrYn().equals("N")) {
			throw new PageNotFoundException();
		}
		boolean isDeleteSuccess = this.qnaService.deleteOneQna(qaId);
		
		if(isDeleteSuccess) {
			logger.info("게시글 삭제 성공");
		} else {
			logger.info("게시글 삭제 실패");
		}
		
		return ApiResponse.Ok(isDeleteSuccess);
	}
	
	@GetMapping("/qna/file/download/{qaId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String qaId) {
		QnaVO qnaVO = this.qnaService.getOneQna(qaId, false);
		if(qnaVO == null) {
			throw new PageNotFoundException();
		}
		if(qnaVO.getFileName() == null || qnaVO.getFileName().length() == 0) {
			throw new PageNotFoundException();
		}
		return this.fileHandler.download(qnaVO.getOriginFileName(), qnaVO.getFileName());
	}
	
	
	private Map<String, List<String>> qnaValidator(QnaVO qnaVO) {

		Validator<QnaVO> validator = new Validator<>(qnaVO);
		validator.add("prjId", Type.NOT_EMPTY, "프로젝트는 필수 입력값입니다")
				.add("qaTtl", Type.NOT_EMPTY, "제목은 필수 입력값입니다")
				.add("qaCntnt", Type.NOT_EMPTY, "내용은 필수 입력값입니다")
				.start();

		if (validator.hasErrors()) {
			return validator.getErrors();

		} else {
			return null;
		}
	}

}
