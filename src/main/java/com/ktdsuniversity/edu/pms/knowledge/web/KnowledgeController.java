package com.ktdsuniversity.edu.pms.knowledge.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.AccessDeniedException;
import com.ktdsuniversity.edu.pms.exceptions.EmployeeNotLoggedInException;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.knowledge.service.KnowledgeService;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeListVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeRecommendVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.SearchKnowledgeVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

@Controller
public class KnowledgeController {

	Logger logger = LoggerFactory.getLogger(KnowledgeController.class);

	@Autowired
	private FileHandler fileHandler;

	@Autowired
	private KnowledgeService knowledgeService;

	@Autowired
	private RequirementService requirementService;

	@Autowired
	private ProjectService projectService;

	// 목록 조회
	@GetMapping("/knowledge")
	public String viewKnowledgeListPage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, Model model, SearchKnowledgeVO searchKnowledgeVO,
			@RequestParam(required = false) String knlId) {
		
		ProjectListVO projectList = this.projectService.getAllProject();
		if (!employeeVO.getAdmnCode().equals("301")) {//관리자가 아니면
			searchKnowledgeVO.setEmpId(employeeVO.getEmpId());
			projectList.setProjectList(this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId()));
		} else {} 
		
//		projectList.setProjectList(
//				projectList.getProjectList().stream().filter((project) -> project.getIsYn().equals("Y")).toList());
		projectList.setProjectList(
				projectList.getProjectList().stream().toList());
		
		
		List<RequirementVO> requirementList = this.requirementService.getAllRequirement();
		if (!employeeVO.getAdmnCode().equals("301")) {//관리자가 아니면
			requirementList=  this.requirementService.getAllRequirementByTeammateId(employeeVO.getEmpId());
		}
		if (!employeeVO.getAdmnCode().equals("301")) {//관리자가 아니면
			searchKnowledgeVO.setEmpId(employeeVO.getEmpId());
		}
		
		KnowledgeListVO knowledgeList = this.knowledgeService.searchAllKnowledge(searchKnowledgeVO);

		model.addAttribute("projectList", projectList)
			 .addAttribute("requirementList", requirementList)
			 .addAttribute("knowledgeList", knowledgeList);
//			 .addAttribute("searchKnowledgeVO", searchKnowledgeVO);
		return "/knowledge/knowledgelist";
	}

	// 게시글별 상세 조회
	@GetMapping("/knowledge/view")
	public String viewDetailKnowledgeListPage(@RequestParam String knlId, Model model,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		KnowledgeVO knowledgeVO = this.knowledgeService.getOneKnowledge(knlId, true);
		
		// 유저 검증
		if (employeeVO == null || employeeVO.getLgnYn() == "N") {
			throw new EmployeeNotLoggedInException();
		}

		// knowledge/view 페이지에 데이터를 전송.
		model.addAttribute("knowledgeVO", knowledgeVO);
		// model.addAttribute("recommendCount", recommendCount);
		// return new AjaxResponse().append("oneKnowledge", knowledgeVO);
		return "/knowledge/knowledgeview";
	}

	// 글 작성 페이지
	@GetMapping("/knowledge/write")
	public String viewKnowledgeWritePage(Model model) {

		List<RequirementVO> requirementList = this.requirementService.getAllRequirement();
//		requirementList.stream().filter( rqm-> rqm.getProjectVO().getIsYn().equals("Y")).toList();
		requirementList.stream().toList();
		
		model.addAttribute("requirement", requirementList);

		return "/knowledge/knowledgewrite";
	}

	// 글 작성
	@ResponseBody
	@PostMapping("/ajax/knowledge/write")
	public AjaxResponse doKnowledgeWrite(KnowledgeVO knowledgeVO,@RequestParam(value="file", required=false) MultipartFile file,
											Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		Map<String, List<String>> error = this.knowledgeValidator(knowledgeVO);
		if (error != null) {
			return new AjaxResponse().append("error", error);
		}
		
		// 유저 검증
		knowledgeVO.setCrtrId(employeeVO.getEmpId());
//		knowledgeVO.setIsMngr(employeeVO.getEmpId());
		if (!knowledgeVO.getCrtrId().equals(employeeVO.getEmpId()) && employeeVO.getMngrYn().equals("N")) {
			throw new AccessDeniedException();
		}
		
//		// 로그인 검증
//		if (employeeVO == null || employeeVO.getLgnYn() == "N") {
//			throw new EmployeeNotLoggedInException();
//		}

		boolean isCreateSuccess = this.knowledgeService.createNewKnowledge(knowledgeVO, file);
//		if (isCreateSuccess) {
//			logger.info("글 등록이 완료되었습니다.");
//		} else {
//			logger.info("글 등록이 실패되었습니다.");
//		}

		String knlId = knowledgeVO.getKnlId();

		return new AjaxResponse().append("result", isCreateSuccess);
		// return "redirect:/knowledge?knlId=" + knlId;

	}

	// 글 수정 페이지
	@GetMapping("/knowledge/modify/{knlId}")
	public String viewKnowledgeModify(@PathVariable String knlId, Model model,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		KnowledgeVO knowledgeVO = this.knowledgeService.getOneKnowledge(knlId, false);

		if (!employeeVO.getEmpId().equals(knowledgeVO.getCrtrId())
				&& employeeVO.getMngrYn().equals("N")) {
			throw new AccessDeniedException();
		}

		// 게시글의 정보를 화면에 보내준다.
		model.addAttribute("knowledgeVO", knowledgeVO);

		return "knowledge/knowledgemodify";

	}

	// 글 수정 작성 페이지
	@ResponseBody
	@PostMapping("/ajax/knowledge/modify")
	public AjaxResponse doKnowledgeModify( String knlId, Model model, MultipartFile file,
			KnowledgeVO knowledgeVO, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		KnowledgeVO originKnowledgeVO = this.knowledgeService.getOneKnowledge(knlId, false);
		// 유저 검증
		if (!originKnowledgeVO.getCrtrId().equals(employeeVO.getEmpId()) && !employeeVO.getMngrYn().equals('N')) {
			throw new PageNotFoundException();
		}

		Map<String, List<String>> error = this.knowledgeValidator(knowledgeVO);
		if (error != null) {
			return new AjaxResponse().append("error", error);
		}

		// Command Object 에는 전달된 knlId가 없으니 @PathVariable로 전달된 knlId를 세팅해준다.
		knowledgeVO.setKnlId(knlId);
		knowledgeVO.setCrtrId(employeeVO.getEmpId());

		boolean isUpdateSuccess = this.knowledgeService.updateOneKnowledge(knowledgeVO, file);

		if (isUpdateSuccess) {
			logger.info("수정이 완료되었습니다!");
		} else {
			logger.info("수정이 실패되었습니다!");
		}

		// return "redirect:/knowledge/view?knlId=" + knlId;
		return new AjaxResponse().append("result", isUpdateSuccess);
	}

	// 글 삭제
	@GetMapping("/knowledge/delete/{knlId}")
	public String doKnowledgeDelete(@PathVariable String knlId, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		KnowledgeVO originKnowledgeVO = this.knowledgeService.getOneKnowledge(knlId, false);

		// 유저 검증
		if (!originKnowledgeVO.getCrtrId().equals(employeeVO.getEmpId()) && employeeVO.getMngrYn().equals("N")) {
			throw new PageNotFoundException();
		}

		boolean isDeleteSuccess = this.knowledgeService.deleteOneKnowledge(knlId);

		if (isDeleteSuccess) {
			logger.info("게시글 삭제 성공!");
		} else {
			logger.info("게시글 삭제 실패!");
		}

		return "redirect:/knowledge";
	}

	// 게시글 일괄 삭제
	@ResponseBody
	@PostMapping("/ajax/knowledge/delete/massive")
	public AjaxResponse doDeleteMassive(@RequestParam("deleteItems[]") List<String> deleteItems,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		if (employeeVO.getMngrYn().equals("N")) {
			throw new PageNotFoundException();
		}

		boolean deleteResult = this.knowledgeService.deleteManyKnowledge(deleteItems);

		return new AjaxResponse().append("result", deleteResult);
	}

	// 추천하기
	// @ResponseBody
	// @PutMapping("/ajax/knowledge/recommend/{knlId}")
	// public AjaxResponse doRecommendKnowledge(@PathVariable("knlId") String knlId,
	// @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
	//
	// // 유저 검증
	//// if(employeeVO == null || employeeVO.getLgnYn() == "N") {
	//// throw new EmployeeNotLoggedInException();
	//// }
	//
	// KnowledgeVO knowledgeVO = this.knowledgeService.getOneKnowledge(knlId,
	// false);
	//
	// // 이번에 업데이트된 추천수(업데이트 성공 하면 1을 리턴함)
	// // 쿼리에서 update는 update된 row수를 리턴한다.
	// // 추천은 1회당 1건에 대해서만 업데이트가 이루어지므로, 성공하면 1이 리턴된다.
	// int successCount = this.knowledgeService.recommendOneKnowledge(knlId);
	//
	// final var result = knowledgeVO.getKnlRecCnt() + successCount;
	//
	// return new AjaxResponse().append("result", result);
	// }

	// 1사원 1추천
	@ResponseBody
	@PostMapping("/knowledge/recommend/{pPostId}")
	public AjaxResponse getRecommendKnowledge(@PathVariable String pPostId,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

 		KnowledgeRecommendVO knowledgeRecommendVO = new KnowledgeRecommendVO();

		knowledgeRecommendVO.setpPostId(pPostId);
		knowledgeRecommendVO.setCrtrId(employeeVO.getEmpId());

		boolean isRecommend = knowledgeService.updateRecommend(knowledgeRecommendVO);
		int recResult = knowledgeService.getKnowledgeRecommendCount(pPostId);

		// 해당 사원이 이미 추천을 했는지 확인
		if (isRecommend) {
			return new AjaxResponse().append("result", "추천이 완료되었습니다.").append("count", recResult);
		} else {
			return new AjaxResponse().append("result", "이미 추천하셨습니다.").append("count", recResult);
		}

	}

	// 파일 다운로드
	@GetMapping("/knowledge/file/download/{knlId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String knlId) {

		// 파일 다운로드를 위해서 id 값으로 게시글을 조회한다.
		KnowledgeVO knowledgeVO = this.knowledgeService.getOneKnowledge(knlId, false);

		// 만약 게시글이 존재하지 않다면 "잘못된 접근입니다." 에러
		if (knowledgeVO == null) {
			throw new PageNotFoundException();
		}

		// 첨부된 파일이 없을 경우에도 "잘못된 접근입니다." 에러
		if (knowledgeVO.getFileName() == null || knowledgeVO.getFileName().length() == 0) {
			throw new PageNotFoundException();
		}

		// 첨부된 파일이 있을 경우엔 파일을 사용자에게 보내준다. (Download)
		return this.fileHandler.download(knowledgeVO.getOriginFileName(), knowledgeVO.getFileName());
	}

	// 엑셀 일괄 다운로드
	@GetMapping("/knowledge/excel/download")
	public ResponseEntity<Resource> downloadExcelFile() throws Exception {

		KnowledgeListVO knowledgeListVO = this.knowledgeService.getAllKnowledge();

		// xlsx 문서 생성
		Workbook workbook = new SXSSFWorkbook(-1);

		// 시트 생성
		Sheet sheet = workbook.createSheet("게시글 목록");

		// 행 생성
		Row row = sheet.createRow(0);

		// 타이틀 생성
		Cell cell = row.createCell(0);
		cell.setCellValue("게시글 ID");

		cell = row.createCell(1);
		cell.setCellValue("제목");

		cell = row.createCell(2);
		cell.setCellValue("첨부파일명");

		cell = row.createCell(3);
		cell.setCellValue("등록자 ID");

		cell = row.createCell(4);
		cell.setCellValue("조회수");

		cell = row.createCell(5);
		cell.setCellValue("추천수");

		cell = row.createCell(6);
		cell.setCellValue("등록일");

		cell = row.createCell(7);
		cell.setCellValue("수정일");

		// 데이터 행 만들고 쓰기
		List<KnowledgeVO> knowledgeList = knowledgeListVO.getKnowledgeList();
		int rowIndex = 1;

		for (KnowledgeVO knowledgeVO : knowledgeList) {
			row = sheet.createRow(rowIndex);

			cell = row.createCell(0);
			cell.setCellValue("" + knowledgeVO.getKnlId());

			cell = row.createCell(1);
			cell.setCellValue("" + knowledgeVO.getKnlTtl());

			cell = row.createCell(2);
			cell.setCellValue("" + knowledgeVO.getOriginFileName());

			cell = row.createCell(3);
			cell.setCellValue("" + knowledgeVO.getCrtrId());

			cell = row.createCell(4);
			cell.setCellValue("" + knowledgeVO.getKnlCnt());

			cell = row.createCell(5);
			cell.setCellValue("" + knowledgeVO.getKnlRecCnt());

			cell = row.createCell(6);
			cell.setCellValue("" + knowledgeVO.getCrtDt());

			cell = row.createCell(7);
			cell.setCellValue("" + knowledgeVO.getMdfDt());

			rowIndex += 1;
		}

		// 엑셀파일 생성
		File storedFile = fileHandler.getStoredFile("게시글_목록.xlsx");
		OutputStream os = null;

		try {
			os = new FileOutputStream(storedFile);
			workbook.write(os);
		} catch (IOException e) {
			throw new Exception("엑셀파일을 만들 수 없습니다.");
			// throw new PageNotFoundException();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
			}
			if (os != null) {
				try {
					os.flush();
				} catch (IOException e) {
				}
				try {
					os.close();
				} catch (IOException e) {
				}
			}
		}

		return this.fileHandler.download("게시글_목록.xlsx", storedFile.getName());
	}

	// 엑셀 일괄 등록
	// @ResponseBody
	// @PostMapping("/ajax/knowledge/excel/write")
	// public AjaxResponse doExcelUpload(@RequestParam MultipartFile excelFile) {
	//
	//
	// boolean isSuccess = this.knowledgeService.createMassiveKnowledge(excelFile);
	//
	// return new AjaxResponse().append("result", isSuccess).append("next",
	// "/knowledge");
	// }

	private Map<String, List<String>> knowledgeValidator(KnowledgeVO knowledgeVO) {

		Validator<KnowledgeVO> validator = new Validator<>(knowledgeVO);
		validator.add("rqmId", Type.NOT_EMPTY, "요구사항은 필수 입력값입니다")
				.add("knlTtl", Type.NOT_EMPTY, "제목은 필수 입력값입니다")
				.add("knlCntnt", Type.NOT_EMPTY, "내용은 필수 입력값입니다")
				.start();

		if (validator.hasErrors()) {
			return validator.getErrors();

		} else {
			return null;
		}
	}

}
