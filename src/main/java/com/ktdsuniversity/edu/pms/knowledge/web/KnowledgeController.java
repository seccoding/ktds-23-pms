package com.ktdsuniversity.edu.pms.knowledge.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.knowledge.service.KnowledgeService;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeListVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.SearchKnowledgeVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.StringUtil;

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
	public String viewKnowledgeListPage(Model model, SearchKnowledgeVO searchKnowledgeVO) {
		ProjectListVO projectList = this.projectService.getAllProject();
		KnowledgeListVO knowledgeList = this.knowledgeService.searchAllKnowledge(searchKnowledgeVO);
		model.addAttribute("projectList", projectList);
		model.addAttribute("knowledgeList", knowledgeList);
//		model.addAttribute("searchKnowledgeVO", searchKnowledgeVO);
		return "/knowledge/knowledgelist";
	}

	// 게시글별 상세 조회
	@GetMapping("/knowledge/view")
	public String viewDetailKnowledgeListPage(@RequestParam String knlId, Model model) {

		logger.info(knlId);

		KnowledgeVO knowledgeVO = this.knowledgeService.getOneKnowledge(knlId, true);

		// knowledge/view 페이지에 데이터를 전송.
		model.addAttribute("knowledgeVO", knowledgeVO);

		// return new AjaxResponse().append("oneKnowledge", knowledgeVO);
		return "/knowledge/knowledgeview";
	}

	// 글 작성 페이지
	@GetMapping("/knowledge/write")
	public String viewKnowledgeWritePage(Model model) {

		ProjectListVO projectList = projectService.getAllProject();
		List<RequirementVO> requirementList = requirementService.getAllRequirement();

		model.addAttribute("requirement", requirementList);

		return "/knowledge/knowledgewrite";
	}

	// 글 작성 // TO DO!! @SessionAttribute 추가
	@PostMapping("/knowledge/write")
	public String doKnowledgeWrite(KnowledgeVO knowledgeVO, @RequestParam MultipartFile file,
			Model model) {

		// 검사 -> Validator로 추후 수정 가능
		boolean isEmptyTitle = StringUtil.isEmpty(knowledgeVO.getKnlTtl());
		boolean isEmptyContent = StringUtil.isEmpty(knowledgeVO.getKnlCntnt());

		if (isEmptyTitle) {
			model.addAttribute("errorMessage", "제목은 필수 입력 값입니다!");
			model.addAttribute("knowledgeVO", knowledgeVO);
			return "/knowledge/knowledgewrite";
		}

		if (isEmptyContent) {
			model.addAttribute("errorMessage", "내용은 필수 입력 값입니다!");
			model.addAttribute("knowledgeVO", knowledgeVO);
			return "knowledge/knowledgewrite";
		}
		// 세션 검증 시 수정해야 함!!!
		knowledgeVO.setCrtrId("system01");

		boolean isCreateSuccess = this.knowledgeService.createNewKnowledge(knowledgeVO, file);
		if (isCreateSuccess) {
			logger.info("글 등록이 완료되었습니다.");
		} else {
			logger.info("글 등록이 실패되었습니다.");
		}

		String knlId = knowledgeVO.getKnlId();

		return "redirect:/knowledge?knlId=" + knlId;

	}

	// 추천하기 // TO DO!!! @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO
	@ResponseBody
	@PutMapping("/ajax/Knowledge/recommend/{knlId}")
	public AjaxResponse doRecommendKnowledge(@PathVariable("knlId") String knlId) {

		KnowledgeVO knowledgeVO = this.knowledgeService.getOneKnowledge(knlId, false);

		// 이번에 업데이트된 추천수(업데이트 성공 하면 1을 리턴함)
		// 쿼리에서 update는 update된 row수를 리턴한다.
		// 추천은 1회당 1건에 대해서만 업데이트가 이루어지므로, 성공하면 1이 리턴된다.
		int successCount = this.knowledgeService.recommendOneKnowledge(knlId);

		final var result = knowledgeVO.getKnlRecCnt() + successCount;

		return new AjaxResponse().append("result", result);
	}

	// 글 수정 페이지 // @SessionAttribute 추가 예정
	@GetMapping("/knowledge/modify/{knlId}")
	public String viewKnowledgeModify(@PathVariable String knlId, Model model) {

		KnowledgeVO knowledgeVO = this.knowledgeService.getOneKnowledge(knlId, false);

		// if(! knowledgeVO.getKnlId().equals(employeeVO.getEmpId()) && !
		// employeeVO.getAdmnCode().equals(301)) {
		// throw new PageNotFoundException();
		// }

		// 게시글의 정보를 화면에 보내준다.
		model.addAttribute("knowledgeVO", knowledgeVO);

		return "knowledge/knowledgemodify";

	}

	// 글 수정 작성 페이지
	@PostMapping("/knowledge/modify/{knlId}") // TO DO!!! @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO
	public String doKnowledgeModify(@PathVariable String knlId, Model model, @RequestParam MultipartFile file,
			KnowledgeVO knowledgeVO) {

		KnowledgeVO originKnowledgeVO = this.knowledgeService.getOneKnowledge(knlId, false);

		// TO DO !! 1. 유저 검증 코드 부분
		// if(! originKnowledgeVO.getKnlId().equals(employeeVO.getEmpId()) && !
		// employeeVO.getAdmnCode().equals(301)) {
		// throw new PageNotFoundException();
		// }

		// 수동 검사 -> Validator로 추후 수정 가능
		boolean isEmptyTitle = StringUtil.isEmpty(knowledgeVO.getKnlTtl());
		boolean isEmptyContent = StringUtil.isEmpty(knowledgeVO.getKnlCntnt());

		if (isEmptyTitle) {
			model.addAttribute("errorMessage", "제목은 필수 입력 값입니다!");
			model.addAttribute("knowledgeVO", knowledgeVO);
			return "knowledge/knowledgemodify";
		}

		if (isEmptyContent) {
			model.addAttribute("errorMessage", "내용은 필수 입력 값입니다!");
			model.addAttribute("knowledgeVO", knowledgeVO);
			return "knowledge/knowledgemodify";
		}
		// Command Object 에는 전달된 knlId가 없으니 @PathVariable로 전달된 knlId를 세팅해준다.
		knowledgeVO.setKnlId(knlId);

		boolean isUpdateSuccess = this.knowledgeService.updateOneKnowledge(knowledgeVO, file);

		if (isUpdateSuccess) {
			logger.info("수정이 완료되었습니다!");
		} else {
			logger.info("수정이 실패되었습니다!");
		}

		return "redirect:/knowledge/view?knlId=" + knlId;
	}

	// 글 삭제
	@GetMapping("/knowledge/delete/{knlId}") // TO DO!!! @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO
	public String doKnowledgeDelete(@PathVariable String knlId) {

		boolean isDeleteSuccess = this.knowledgeService.deleteOneKnowledge(knlId);

		// KnowledgeVO originKnowledgeVO = this.knowledgeService.getOneKnowledge(knlId,
		// false);

		// TO DO !! 1. 유저 검증 코드 부분
		// if(! originKnowledgeVO.getKnlId().equals(employeeVO.getEmpId()) && !
		// employeeVO.getAdmnCode().equals(301)) {
		// throw new PageNotFoundException();
		// }

		if (isDeleteSuccess) {
			logger.info("게시글 삭제 성공!");
		} else {
			logger.info("게시글 삭제 실패!");
		}

		return "redirect:/knowledge";
	}


	// 게시글 일괄 삭제
	@ResponseBody
	@PostMapping("/ajax/knowledge/delete/massive") // @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO
	public AjaxResponse doDeleteMassive(@RequestParam ("deleteItems[]") List<String> deleteItems) {
		
//		if (EmployeeVO.getAdminYn().equals('N')) {
//			throw new PageNotFoundException();
//		}
		
		boolean deleteResult = this.knowledgeService.deleteManyKnowledge(deleteItems);
		
		return new AjaxResponse().append("result", deleteResult );
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
		return this.fileHandler.download(knowledgeVO.getOriginFileName(), knowledgeVO.getFileName() );
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
//			throw new PageNotFoundException();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {}
			if (os != null) {
				try {
					os.flush();
				} catch (IOException e) {}
				try {
					os.close();
				} catch (IOException e) {}
			}
		}
		
		return this.fileHandler.download("게시글_목록.xlsx", storedFile.getName());
	}
	
	
	// 엑셀 일괄 등록
	@ResponseBody
	@PostMapping("/ajax/knowledge/excel/write")
	 public AjaxResponse doExcelUpload(@RequestParam MultipartFile excelFile) {
		
	
	 boolean isSuccess = this.knowledgeService.createMassiveKnowledge(excelFile);
	
	 return new AjaxResponse().append("result", isSuccess).append("next", "/knowledge");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
