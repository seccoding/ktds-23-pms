package com.ktdsuniversity.edu.pms.knowledge.api;

import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.el.util.Validation;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.knowledge.service.KnowledgeService;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeListVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeRecommendVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.SearchKnowledgeVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.ValidationUtils;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;
import ch.qos.logback.classic.Logger;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;




@ RestController
@RequestMapping("/api/v1")
public class Apiknowledge {
	@Autowired
	private FileHandler fileHandler;
	
	@Autowired
	private KnowledgeService knowledgeService;
	
	// 지식관리 리스트
	@GetMapping("/knowledge")
	public ApiResponse getKnoeledge(SearchKnowledgeVO searchKnowledgeVO) {

		KnowledgeListVO knowledgeVO=this.knowledgeService.searchAllKnowledge(searchKnowledgeVO);
		
		return ApiResponse.Ok (knowledgeVO.getKnowledgeList(), knowledgeVO.getKnowledgeCnt(),
								searchKnowledgeVO.getPageCount(),
								searchKnowledgeVO.getPageNo() < searchKnowledgeVO.getPageCount() - 1); 
				
		
	}
	
	// 지식관리 상세보기(view)
	@GetMapping("/knowledge/{knlId}")
	public ApiResponse viewDetailKnowledgeListPage(@PathVariable String knlId) {

		KnowledgeVO   knowledgeVO=this.knowledgeService.getOneKnowledge(knlId, false);
		
		return ApiResponse.Ok(knowledgeVO,  knowledgeVO == null ? 0 : 1);
		
	}
	
	// 지식관리 추가(insert)
	@PostMapping("/knowledge/insert")
	public ApiResponse doKnowledgeWrite(KnowledgeVO knowledgeVO
			,@RequestParam(required = false) MultipartFile file,
			Authentication authentication) {
				
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String employeeVO = ((SecurityUser) userDetails).getEmployeeVO().getEmpId();
		System.out.println(employeeVO);
		
		knowledgeVO.setCrtrId(employeeVO);
		boolean isCreateSuccess=this.knowledgeService.createNewKnowledge(knowledgeVO, file);
		
		return ApiResponse.Ok(isCreateSuccess);
		
	}
	
	//	지식관리 수정(update)
	@PutMapping("/knowledge/modify/{knlId}")
	public ApiResponse viewKnowledgeModify(@PathVariable String knlId
			,@RequestParam(required = false) MultipartFile file,
			KnowledgeVO knowledgevo,Authentication authentication) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employee = ((SecurityUser) userDetails).getEmployeeVO();
		
		String name=this.knowledgeService.findid(knlId);
		List<String> errorMessage = new ArrayList<>();
		if(!employee.getEmpId().equals(name)) {
			errorMessage.add("글 작성자만 수정이 가능합니다");  // More specific error message
		    return ApiResponse.BAD_REQUEST(errorMessage);
		}
			
		knowledgevo.setKnlId(knlId);
		knowledgevo.setCrtrId(employee.getEmpId());
		
		boolean isUpdateSuccess = this.knowledgeService.updateOneKnowledge(knowledgevo, file);
	
		return ApiResponse.Ok(isUpdateSuccess);
		
		
	}
	
	//삭제
	@GetMapping("/knowledge/delete/{knlId}")
	public  ApiResponse doKnowledgeModify(@PathVariable String knlId, Authentication authentication) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employee = ((SecurityUser) userDetails).getEmployeeVO();
		
		String name=this.knowledgeService.findid(knlId);
		List<String> errorMessage = new ArrayList<>();
		if(!employee.getEmpId().equals(name)) {
			errorMessage.add("글 작성자만 삭제 가능합니다");  
		    return ApiResponse.BAD_REQUEST(errorMessage);
		}
		
		boolean isDeleteSuccess= this.knowledgeService.deleteOneKnowledge(knlId);
		
		return ApiResponse.Ok(isDeleteSuccess);
	}
	
	// 1사원 1추천
	@PostMapping("/knowledge/recommend/{knlId}")
	public ApiResponse getRecommendKnowledge(@PathVariable String knlId, Authentication authentication) {
			
		KnowledgeRecommendVO knowledgeRecommendVO = new KnowledgeRecommendVO();
		
		knowledgeRecommendVO.setpPostId(knlId);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String employeeVO = ((SecurityUser) userDetails).getEmployeeVO().getEmpId();
		knowledgeRecommendVO.setCrtrId(employeeVO);
		
		boolean isRecommend = knowledgeService.updateRecommend(knowledgeRecommendVO);
		int recResult = knowledgeService.getKnowledgeRecommendCount(knlId);
		
		//추천 중복체크		
		List<String> errorMessage = new ArrayList<>();
		if (!isRecommend) {
			errorMessage.add("이미 존재하는 추천 입니다.");  // More specific error message
		     return ApiResponse.BAD_REQUEST(errorMessage);
		}
		
		return ApiResponse.Ok(recResult);
		
	}
	
	
			
	
			
	
	
	
	
	
}


