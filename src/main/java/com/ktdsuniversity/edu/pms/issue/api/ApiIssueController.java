package com.ktdsuniversity.edu.pms.issue.api;

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
import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.AccessDeniedException;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.issue.service.IssueService;
import com.ktdsuniversity.edu.pms.issue.vo.IssueListVO;
import com.ktdsuniversity.edu.pms.issue.vo.IssueVO;
import com.ktdsuniversity.edu.pms.issue.vo.SearchIssueVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

@RestController
@RequestMapping("/api/v1")
public class ApiIssueController {
	@Autowired
	private FileHandler fileHandler;
	@Autowired
	private IssueService issueService;
	@Autowired
	private RequirementService requirementService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CommonCodeService commonCodeService;
	
	//이슈 리스트 
	@GetMapping("/issue")
	public ApiResponse viewSearchIssueListPage(SearchIssueVO searchIssueVO, Authentication authentication) {
	
	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
	
	ProjectListVO projectList = this.projectService.getAllProject();
	if(!employeeVO.getAdmnCode().equals("301")) {
		searchIssueVO.setEmpId(employeeVO.getEmpId());
		projectList.setProjectList(this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId()));
	} else {}
	projectList.setProjectList(
			projectList.getProjectList().stream().toList());
	
	List<RequirementVO> requirementList = this.requirementService.getAllRequirement();
	if(!employeeVO.getAdmnCode().equals("301")) {
		requirementList = this.requirementService.getAllRequirementByTeammateId(employeeVO.getEmpId());
	}
	if(!employeeVO.getAdmnCode().equals("301")) {
		searchIssueVO.setEmpId(employeeVO.getEmpId());
	}
	IssueListVO issueList = this.issueService.searchAllIssue(searchIssueVO);
	
	return ApiResponse.Ok(issueList.getIssueList(), issueList.getIssueCnt(), 
			 searchIssueVO.getPageCount(), searchIssueVO.getPageNo() < searchIssueVO.getPageCount() -1);
	}
	
	// 이슈 상세페이지 
	@GetMapping("/issue/view")
	public ApiResponse viewIssueDetail(@RequestParam String isId, Authentication authentication) {
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		IssueVO issueVO = this.issueService.getOneIssue(isId, true);
		return ApiResponse.Ok(issueVO);
	}
	
	//이슈 작성페이지
	@GetMapping("/issue/write")
	public ApiResponse viewissueWritePage(Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		List<RequirementVO> requriementList = this.requirementService.getAllRequirement();
		requriementList.stream().toList();
		return ApiResponse.Ok(requriementList);
	}
	
	// 요구사항에 속한 팀메이트 
	@GetMapping("/issue/write/{rqmId}")
	public ApiResponse getRequirementTeammate(@PathVariable String rqmId, Authentication authentication) {
		
		List<RequirementVO> rqmList = this.requirementService.getAllRequirementByTeammateId(rqmId);
		return ApiResponse.Ok(rqmList);
	}
	
	@PostMapping("/issue/write")
	public ApiResponse writeIssue(IssueVO issueVO, Authentication authentication,
			@RequestParam(required = false) MultipartFile file) throws Exception {
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		Map<String, List<String>> error = this.issueValidator(issueVO);
		if(error != null) {
			return ApiResponse.Ok(error);
		}
		issueVO.setCrtrId(employeeVO.getEmpId());
		issueVO.setIsMngr(employeeVO.getEmpId());
		if(!issueVO.getCrtrId().equals(employeeVO.getEmpId()) && employeeVO.getMngrYn().equals("N")) {
			throw new AccessDeniedException();
		}
		boolean isSuccess = this.issueService.createNewIssue(issueVO, file);
		
		return ApiResponse.Ok(isSuccess);
	}
	
	@GetMapping("/issue/modify")
	public ApiResponse viewModifyIssuePage(@RequestParam String isId, Authentication authentication ) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		IssueVO issueVO = this.issueService.getOneIssue(isId, false);
		
		if(!employeeVO.getEmpId().equals(issueVO.getCrtrId()) && employeeVO.getMngrYn().equals("N")) {
			throw new AccessDeniedException();
		}
		return ApiResponse.Ok(issueVO);
	}
	
	@PostMapping("issue/modify")
	public ApiResponse ModifyIssue(@RequestParam String isId, IssueVO issueVO, 
			@RequestParam(required = false) MultipartFile file, Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		IssueVO originalIssueVO = this.issueService.getOneIssue(isId, false);
		if(!originalIssueVO.getCrtrId().equals(employeeVO.getEmpId()) && employeeVO.getMngrYn().equals("N")) {
			throw new AccessDeniedException();
		}
		
		issueVO.setIsId(isId);
		issueVO.setMdfrId(employeeVO.getEmpId());
		
		boolean isUpdateSuccess = this.issueService.updateOneIssue(issueVO, file);
		
		return ApiResponse.Ok(isUpdateSuccess);
	}
	
	@GetMapping("/issue/delete/{isId}")
	public ApiResponse deleteIssue(@PathVariable String isId, 
								   Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		if(employeeVO.getMngrYn().equals("N")) {
			throw new AccessDeniedException();
		}
		boolean isSuccess = this.issueService.deleteOneIssue(isId);
		return ApiResponse.Ok(isSuccess);
	}
	
	@PostMapping("/issue/delete/massive")
	public ApiResponse deleteMassive(@RequestParam("deleteItems[]") List<String> deleteItems,
									Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		if(employeeVO.getMngrYn().equals("N")) {
			throw new AccessDeniedException();
		}
		boolean deleteResult = this.issueService.deleteManyIssue(deleteItems);
		return ApiResponse.Ok(deleteResult);
	}
	
	@GetMapping("issue/file/download/{isId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String isId) {
		IssueVO issueVO = this.issueService.getOneIssue(isId, false);
		if(issueVO == null) {
			throw new PageNotFoundException();
		}
		if(issueVO.getFileName() == null || issueVO.getFileName().length() == 0) {
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
	
	
	private Map<String, List<String>> issueValidator(IssueVO issueVO) {
		Validator<IssueVO> validator = new Validator<>(issueVO);
		validator.add("isTtl", Type.NOT_EMPTY, "제목은 필수 입력값입니다")
				.add("isCntnt", Type.NOT_EMPTY, "내용은 필수 입력값입니다")
				.add("rqmId", Type.NOT_EMPTY, "요구사항은 필수 입력값입니다")
				.start();
		if (validator.hasErrors()) {
			return validator.getErrors();
		} 
		else {
			return null;
		}
	}
	

}
