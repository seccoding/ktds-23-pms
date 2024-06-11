package com.ktdsuniversity.edu.pms.qna.web;

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
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.qna.service.QnaService;
import com.ktdsuniversity.edu.pms.qna.vo.QnaListVO;
import com.ktdsuniversity.edu.pms.qna.vo.QnaRecommendVO;
import com.ktdsuniversity.edu.pms.qna.vo.QnaVO;
import com.ktdsuniversity.edu.pms.qna.vo.SearchQnaVO;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

@Controller
public class QnaController {

	Logger logger = LoggerFactory.getLogger(QnaController.class);

	@Autowired
	private FileHandler fileHandler;

	@Autowired
	private QnaService qnaService;

	@Autowired
	private RequirementService requirementService;

	@Autowired
	private ProjectService projectService;

	// 전체 리스트 조회
	@GetMapping("/qna")
	public String viewQnaListPage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, Model model, SearchQnaVO searchQnaVO,
									@RequestParam(required = false) String qaId) {

		ProjectListVO projectList = this.projectService.getAllProject();
		if (!employeeVO.getAdmnCode().equals("301")) {//관리자가 아니면
			searchQnaVO.setEmpId(employeeVO.getEmpId());
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
			searchQnaVO.setEmpId(employeeVO.getEmpId());
		}
		
		QnaListVO qnaList = this.qnaService.searchAllQna(searchQnaVO);

		model.addAttribute("projectList", projectList)
			 .addAttribute("requirementList", requirementList)
			 .addAttribute("qnaList", qnaList)
			 .addAttribute("searchQnaVO", searchQnaVO);

		return "/qna/qnalist";
	}

	// 게시글별 상세 조회
	@GetMapping("/qna/view")
	public String viewDetailQnaListPage(@RequestParam String qaId, Model model,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		QnaVO qnaVO = this.qnaService.getOneQna(qaId, true);

		// qna/view 페이지에 데이터를 전송.
		model.addAttribute("qnaVO", qnaVO);
		// model.addAttribute("recommendCount", recommendCount);
		// return new AjaxResponse().append("oneQna", qnaVO);
		return "/qna/qnaview";
	}

	// 글 작성 view 페이지
	@GetMapping("/qna/write")
	public String viewQnaWrite(Model model) {

		List<RequirementVO> requirementList = this.requirementService.getAllRequirement();
//		requirementList.stream().filter( rqm-> rqm.getProjectVO().getIsYn().equals("Y")).toList();
		requirementList.stream().toList();

		model.addAttribute("requirement", requirementList);

		return "/qna/qnawrite";
	}

	// 글 작성
	@ResponseBody
	@PostMapping("/ajax/qna/write")
	public AjaxResponse doQnaWrite(QnaVO qnaVO, @RequestParam(value="file", required=false) MultipartFile file,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, Model model) {

		Map<String, List<String>> error = this.qnaValidator(qnaVO);
		if (error != null) {
			return new AjaxResponse().append("error", error);
		}
		
		// 유저 검증
		qnaVO.setCrtrId(employeeVO.getEmpId());
		// qnaVO.setIsMngr(employeeVO.getEmpId());
		if (!qnaVO.getCrtrId().equals(employeeVO.getEmpId()) && employeeVO.getMngrYn().equals("N")) {
			throw new AccessDeniedException();
		}

		boolean isCreateSuccess = this.qnaService.createNewQna(qnaVO, file);
		if (isCreateSuccess) {
			logger.info("글 등록이 완료되었습니다.");
		} else {
			logger.info("글 등록이 실패되었습니다.");
		}

		String qaId = qnaVO.getQaId();

		// return "redirect:/qna?qaId=" + qaId;
		return new AjaxResponse().append("result", isCreateSuccess);

	}

	// 추천하기
	// @ResponseBody
	// @PutMapping("/ajax/qna/recommend/{qaId}")
	// public AjaxResponse doRecommendQna(@PathVariable("qaId") String qaId,
	// @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO)
	// throws Exception {
	//
	// // 유저 검증
	// if(employeeVO == null || employeeVO.getLgnYn() == "N") {
	// throw new EmployeeNotLoggedInException();
	// }
	//
	// // 추천 여부 검증
	//// if(recommendVO.getCrtrId().contains(employeeVO.getEmpId())) {
	//// throw new Exception("이미 추천을 누르셨습니다.");
	//// }
	//
	// QnaVO qnaVO = this.qnaService.getOneQna(qaId, false);
	//
	// // 이번에 업데이트된 추천수(업데이트 성공 하면 1을 리턴함)
	// // 쿼리에서 update는 update된 row수를 리턴한다.
	// // 추천은 1회당 1건에 대해서만 업데이트가 이루어지므로, 성공하면 1이 리턴된다.
	// int successCount = this.qnaService.recommendOneQna(qaId);
	//
	// final var result = qnaVO.getQaRecCnt() + successCount;
	//
	// return new AjaxResponse().append("result", result);
	// }

	// 1사원 1추천
	@ResponseBody
	@PostMapping("/qna/recommend/{pPostId}")
	public AjaxResponse getRecommendQna(@PathVariable String pPostId,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		QnaRecommendVO qnaRecommendVO = new QnaRecommendVO();

		qnaRecommendVO.setpPostId(pPostId);
		qnaRecommendVO.setCrtrId(employeeVO.getEmpId());

		boolean isRecommend = qnaService.updateRecommend(qnaRecommendVO);
		int recResult = qnaService.getQnaRecommendCount(pPostId);

		// 해당 사원이 이미 추천을 했는지 확인
		if (isRecommend) {
			return new AjaxResponse().append("result", "추천이 완료되었습니다.").append("count", recResult);
		} else {
			return new AjaxResponse().append("result", "이미 추천하셨습니다.").append("count", recResult);
		}

	}

	// 1사원 1추천 취소
	// @ResponseBody
	// @PostMapping("/ajax/qna/recommend/{pPostId}")
	// public AjaxResponse cancelRecommendQna (@PathVariable String pPostId,
	// @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
	//
	// QnaRecommendVO qnaRecommendVO = new QnaRecommendVO();
	//
	// qnaRecommendVO.setpPostId(pPostId);
	// qnaRecommendVO.setCrtrId(employeeVO.getEmpId());
	//
	// boolean isCancelRecommend = qnaService.cancelRecommend(qnaRecommendVO);
	//
	// // 해당 사원이 이미 추천을 취소 했는지 확인
	// if (isCancelRecommend) {
	// return new AjaxResponse().append("result", "추천이
	// 취소되었습니다.").append("resultStatus", true);
	// }
	//
	// }

	// 글 수정 페이지
	@GetMapping("/qna/modify/{qaId}")
	public String viewQnaModify(@PathVariable String qaId, Model model,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		QnaVO qnaVO = this.qnaService.getOneQna(qaId, false);

		// 유저 검증
		if (!employeeVO.getEmpId().equals(qnaVO.getCrtrId())
				&& employeeVO.getMngrYn().equals("N")) {
			throw new AccessDeniedException();
		}

		// 게시글의 정보를 화면에 보내준다.
		model.addAttribute("qnaVO", qnaVO);

		return "qna/qnamodify";

	}

	// 글 수정 작성 페이지
	@ResponseBody
	@PostMapping("/ajax/qna/modify")
	public AjaxResponse doQnaModify( String qaId, Model model, MultipartFile file,
			QnaVO qnaVO, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		QnaVO originqnaVO = this.qnaService.getOneQna(qaId, false);

		// 유저 검증
		if (!originqnaVO.getCrtrId().equals(employeeVO.getEmpId()) && !employeeVO.getMngrYn().equals('N')) {
			throw new PageNotFoundException();
		}

		Map<String, List<String>> error = this.qnaValidator(qnaVO);
		if (error != null) {
			return new AjaxResponse().append("error", error);
		}

		// Command Object 에는 전달된 qaId가 없으니 @PathVariable로 전달된 qaId를 세팅해준다.
		qnaVO.setQaId(qaId);
		qnaVO.setCrtrId(employeeVO.getEmpId());

		boolean isUpdateSuccess = this.qnaService.updateOneQna(qnaVO, file);

		if (isUpdateSuccess) {
			logger.info("수정이 완료되었습니다!");
		} else {
			logger.info("수정이 실패되었습니다!");
		}

		// return "redirect:/qna/view?qaId=" + qaId;
		return new AjaxResponse().append("result", isUpdateSuccess);
	}

	// 삭제
	@GetMapping("/qna/delete/{qaId}")
	public String doQnaDelete(@PathVariable String qaId, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		QnaVO originQnaVO = this.qnaService.getOneQna(qaId, false);

		// 유저 검증
		if (!originQnaVO.getCrtrId().equals(employeeVO.getEmpId()) && employeeVO.getMngrYn().equals("N")) {
			throw new PageNotFoundException();
		}

		boolean isDeleteSuccess = this.qnaService.deleteOneQna(qaId);

		if (isDeleteSuccess) {
			logger.info("게시글 삭제 성공!");
		} else {
			logger.info("게시글 삭제 실패!");
		}

		return "redirect:/qna";
	}

	// 게시글 일괄 삭제
	@ResponseBody
	@PostMapping("/ajax/qna/delete/massive")
	public AjaxResponse doDeleteMassive(@RequestParam("deleteItems[]") List<String> deleteItems,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		if (employeeVO.getMngrYn().equals("N")) {
			throw new PageNotFoundException();
		}

		boolean deleteResult = this.qnaService.deleteManyQna(deleteItems);

		return new AjaxResponse().append("result", deleteResult);
	}

	// 파일 다운로드
	@GetMapping("/qna/file/download/{qaId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String qaId) {

		// 파일 다운로드를 위해서 id 값으로 게시글을 조회한다.
		QnaVO qnaVO = this.qnaService.getOneQna(qaId, false);

		// 만약 게시글이 존재하지 않다면 "잘못된 접근입니다." 에러
		if (qnaVO == null) {
			throw new PageNotFoundException();
		}

		// 첨부된 파일이 없을 경우에도 "잘못된 접근입니다." 에러
		if (qnaVO.getFileName() == null || qnaVO.getFileName().length() == 0) {
			throw new PageNotFoundException();
		}

		// 첨부된 파일이 있을 경우엔 파일을 사용자에게 보내준다. (Download)
		return this.fileHandler.download(qnaVO.getOriginFileName(), qnaVO.getFileName());
	}

	// 엑셀 일괄 다운로드
	@GetMapping("/qna/excel/download")
	public ResponseEntity<Resource> downloadExcelFile() throws Exception {

		QnaListVO qnaListVO = this.qnaService.getAllQna();

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
		List<QnaVO> qnaList = qnaListVO.getQnaList();
		int rowIndex = 1;

		for (QnaVO qnaVO : qnaList) {
			row = sheet.createRow(rowIndex);

			cell = row.createCell(0);
			cell.setCellValue("" + qnaVO.getQaId());

			cell = row.createCell(1);
			cell.setCellValue("" + qnaVO.getQaTtl());

			cell = row.createCell(2);
			cell.setCellValue("" + qnaVO.getOriginFileName());

			cell = row.createCell(3);
			cell.setCellValue("" + qnaVO.getCrtrId());

			cell = row.createCell(4);
			cell.setCellValue("" + qnaVO.getQaCnt());

			cell = row.createCell(5);
			cell.setCellValue("" + qnaVO.getQaRecCnt());

			cell = row.createCell(6);
			cell.setCellValue("" + qnaVO.getCrtDt());

			cell = row.createCell(7);
			cell.setCellValue("" + qnaVO.getMdfDt());

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
