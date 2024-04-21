package com.ktdsuniversity.edu.pms.issue.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.issue.service.IssueService;
import com.ktdsuniversity.edu.pms.issue.vo.IssueListVO;
import com.ktdsuniversity.edu.pms.issue.vo.IssueVO;
import com.ktdsuniversity.edu.pms.issue.vo.SearchIssueVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.StringUtil;

@Controller
public class IssueController {
	
	private Logger logger = LoggerFactory.getLogger(IssueController.class);

	@Autowired
	private IssueService issueService;
	
	@Autowired
	private RequirementService requirementService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private FileHandler fileHandler;
	
	@GetMapping("/issue")
	public String viewSearchIssueListPage(Model model, SearchIssueVO searchIssueVO) {
		ProjectListVO projectList = this.projectService.getAllProject();
		IssueListVO issueList = this.issueService.searchAllIssue(searchIssueVO);
		
		model.addAttribute("projectList", projectList);
		model.addAttribute("issueList", issueList);
		
		return "/issue/issuelist";
	}
	
	@GetMapping("/issue/view")
	public String viewIssueDetailPage(@RequestParam String isId, Model model) {
		IssueVO issueVO = this.issueService.getOneIssue(isId, true);
		model.addAttribute("issueVO", issueVO);
		return "issue/issueview";
	}
	
	@GetMapping("/issue/write")
	public String viewIssueWritePage(Model model) {
		
		List<RequirementVO> requirementList = this.requirementService.getAllRequirement();
		model.addAttribute("requirement", requirementList);
		return "/issue/issuewrite";
	}
	
	@PostMapping("/issue/write")
	public String doWriteIssue(IssueVO issueVO, Model model, @RequestParam MultipartFile file) {
		boolean isEmptyTitle = StringUtil.isEmpty(issueVO.getIsTtl());
		boolean isEmptyContent = StringUtil.isEmpty(issueVO.getIsCntnt());
		
		if (isEmptyTitle) {
			model.addAttribute("errorMessage", "제목은 필수 입력 값입니다!");
			model.addAttribute("issueVO", issueVO);
			return "/issue/issuewrite";
		}
		
		if (isEmptyContent) {
			model.addAttribute("errorMessage", "내용은 필수 입력 값입니다!");
			model.addAttribute("issueVO", issueVO);
			return "/issue/issuewrite";
		}
		// 세션 추가 해야함
		issueVO.setCrtrId("system01");
		issueVO.setIsMngr("system01");
		
		boolean isSuccess = this.issueService.createNewIssue(issueVO, file);
		if (isSuccess) {
			logger.info("글 등록 성공!");
		}
		else {
			logger.info("글 등록 실패!");
		}
		
		String isId = issueVO.getIsId();
		
		return "redirect:/issue?isId=" + isId;
	}
	
	@GetMapping("/issue/modify/{isId}")
	public String viewModifyIssuePage(@PathVariable String isId, Model model) {
		
		IssueVO issueVO = this.issueService.getOneIssue(isId, false);
		
		model.addAttribute("issueVO", issueVO);
		return "issue/issuemodify";
	}
	
	@PostMapping("/issue/modify/{isId}")
	public String doModifyIssue(@PathVariable String isId, Model model, @RequestParam MultipartFile file, IssueVO issueVO) {

		boolean isEmptyTitle = StringUtil.isEmpty(issueVO.getIsTtl());
		boolean isEmptyContent = StringUtil.isEmpty(issueVO.getIsCntnt());
		
		if (isEmptyTitle) {
			model.addAttribute("errorMessage", "제목은 필수 입력 값입니다.");
			model.addAttribute("issueVO", issueVO);
			return "issue/issuemofidy";
		}
		
		if (isEmptyContent) {
			model.addAttribute("errorMessage", "내용은 필수 입력 값입니다.");
			model.addAttribute("issueVO", issueVO);
			return "board/boardmodify";
		}
		
		issueVO.setIsId(isId);
		issueVO.setCrtrId("system01");
		issueVO.setMdfrId("system01");
		
		boolean isUpdateSuccess = this.issueService.updateOneIssue(issueVO, file);
		
		if (isUpdateSuccess) {
			logger.info("수정 성공했습니다!");
		}
		else {
			logger.info("수정 실패했습니다!");
		}
		return "redirect:/issue/view?isId=" + isId;
	}
	
	@GetMapping("/issue/delete/{isId}")
	public String doDeleteIssue(@PathVariable String isId) {
		
		boolean isSuccess = this.issueService.deleteOneIssue(isId);
		
		if (isSuccess) {
			logger.info("삭제 성공!");
		}
		else {
			logger.info("삭제 실패!");
		}
		return "redirect:/issue";
	}
	
	@ResponseBody
	@PostMapping("/ajax/issue/delete/massive")
	public AjaxResponse doDeleteMassive(@RequestParam("deleteItems[]") List<Integer> deleteItems) {
		
		boolean deleteResult = this.issueService.deleteManyIssue(deleteItems);

		return new AjaxResponse().append("result", deleteResult);
	}
	
	@GetMapping("/issue/file/download/{isId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String isId) {
		IssueVO issueVO = this.issueService.getOneIssue(isId, false);
		
		if (issueVO == null) {
			throw new PageNotFoundException();
		}
		
		if (issueVO.getFileName() == null || issueVO.getFileName().length() == 0) {
			throw new PageNotFoundException();
		}
		
		return this.fileHandler.download(issueVO.getOriginFileName(), issueVO.getFileName());
	}
	
	@GetMapping("/issue/excel/download")
	public ResponseEntity<Resource> downloadExcelFile() throws Exception {
		IssueListVO issueListVO = this.issueService.getAllIssue();
		
		Workbook workbook = new SXSSFWorkbook(-1);
		Sheet sheet = workbook.createSheet("이슈 목록");
		Row row = sheet.createRow(0);
		
		Cell cell = row.createCell(0);
		cell.setCellValue("이슈ID");

		cell = row.createCell(1);
		cell.setCellValue("제목");

		cell = row.createCell(2);
		cell.setCellValue("첨부파일명");
		
		cell = row.createCell(3);
		cell.setCellValue("내용");

		cell = row.createCell(4);
		cell.setCellValue("작성자");

		cell = row.createCell(5);
		cell.setCellValue("조회수");

		cell = row.createCell(6);
		cell.setCellValue("등록일");

		cell = row.createCell(7);
		cell.setCellValue("수정일");
		
		List<IssueVO> issueList = issueListVO.getIssueList();
		int rowIndex = 1;
		
		for (IssueVO issueVO : issueList) {
			row = sheet.createRow(rowIndex);

			cell = row.createCell(0);
			cell.setCellValue("" + issueVO.getIsId());

			cell = row.createCell(1);
			cell.setCellValue(issueVO.getIsTtl());

			cell = row.createCell(2);
			cell.setCellValue(issueVO.getOriginFileName());

			cell = row.createCell(3);
			cell.setCellValue(issueVO.getIsCntnt());

			cell = row.createCell(4);
			cell.setCellValue(issueVO.getCrtrId());

			cell = row.createCell(5);
			cell.setCellValue(issueVO.getIsCnt());

			cell = row.createCell(6);
			cell.setCellValue(issueVO.getCrtDt());

			cell = row.createCell(7);
			cell.setCellValue(issueVO.getMdfDt());
			rowIndex += 1;
			
		}
		File storedFile = this.fileHandler.makeNewFile("이슈_목록.xlsx");
		OutputStream os = null;
		
		try {
			os = new FileOutputStream(storedFile);
			workbook.write(os);
		} catch (IOException e) {
			throw new Exception("엑셀 파일을 만들 수 없습니다.");
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
		return this.fileHandler.download("이슈_목록.xlsx", storedFile.getName());
	}
}
