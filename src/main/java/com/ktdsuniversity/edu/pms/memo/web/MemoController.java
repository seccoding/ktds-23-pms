package com.ktdsuniversity.edu.pms.memo.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.memo.service.MemoService;
import com.ktdsuniversity.edu.pms.memo.vo.MemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.MemoVO;
import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.RequestUtil;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemoController {
	
	private Logger logger = LoggerFactory.getLogger(MemoController.class);
	
	@Autowired
	private MemoService memoService; 
	
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 보낸 쪽지리스트 보여주는 페이지 
	 */
	@GetMapping("/memo/sent")
	public String viewSentMemoListPage(Model model,
			SearchMemoVO searchMemoVO,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO ) {
		
		searchMemoVO.setEmpId(employeeVO.getEmpId());
		MemoListVO memoListVO =this.memoService.getSentMemoAllsearch(searchMemoVO);
		
		model.addAttribute("memoList", memoListVO );
		model.addAttribute("searchMemoVO", searchMemoVO);
		
		return "memo/memosent";
	}
	
	@GetMapping("/memo/storage")
	public String viewStorageMemoListPage(Model model, SearchMemoVO searchMemoVO,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		
		searchMemoVO.setEmpId(employeeVO.getEmpId());
		MemoListVO memoListVO =this.memoService.getStorageMemoAllsearch(searchMemoVO);
		
		model.addAttribute("memoList", memoListVO );
		model.addAttribute("searchMemoVO", searchMemoVO);
		
		return "memo/memostorage";
	}
	
	@GetMapping("/memo/receive")
	public String viewReceiveMemoListPage(Model model, SearchMemoVO searchMemoVO,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		
		searchMemoVO.setEmpId(employeeVO.getEmpId());
		MemoListVO memoListVO =this.memoService.getReceiveMemoAllsearch(searchMemoVO);
		
		model.addAttribute("memoList", memoListVO );
		model.addAttribute("searchMemoVO", searchMemoVO);
		return "memo/memoreceive";
	}
	
	/**
	 * 쪽지 쓰는화면 보여주기
	 */
	@GetMapping("/memo/write")
	public String viewMemoWritePage(HttpSession session) {
		return "memo/memowrite";
	}
	
	/**
	 * 쪽찌 쓰기 기능 쓰기 완료시 보낸쪽지함으로 이동
	 */
	@PostMapping("/memo/write")
	public String doMemoWrite(@ModelAttribute MemoVO memoVO,
			Model model,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		
		
		memoVO.setEmpId(employeeVO.getEmpId());
		
		
		Validator<MemoVO> validator = new Validator<>(memoVO);
		validator
		.add("memoTtl", Type.NOT_EMPTY, "제목은 필수 입력값입니다.")
		.add("rcvId", Type.NOT_EMPTY, "받는 사람은 필수 입력값입니다.")
//		.add("rcvId", Type.EMPID, "사원 번호에 맞는 형식대로 입력해주세요.")
		.add("memoCntnt", Type.NOT_EMPTY, "내용은 필수 입력값입니다.")
		.start();
	
		
		if(validator.hasErrors()) {
			Map<String, List<String>> errors = validator.getErrors();
			model.addAttribute("errorMessage",errors);
			model.addAttribute("memoVO",memoVO);
			return "memo/memowrite";
		}
		
		boolean isCreateSuccess = this.memoService.writeNewMemo(memoVO);
		if(isCreateSuccess) {
			logger.info("쪽지 쓰기 성공!");
		}
		else {
			logger.info("쪽지 쓰기 실패!");			
		}
		
		return "redirect:/memo/sent";
	}
	
	
	@GetMapping({"/memo/sent/view", "/memo/receive/view", "/memo/storage/view"})
	public String viewMemoDetailPage(@RequestParam() String id, Model model,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		String empId = employeeVO.getEmpId();
		MemoVO memoVO = this.memoService.getOneMemo(id,empId);
	
		model.addAttribute("memoVO", memoVO);
		model.addAttribute("url", RequestUtil.getRequest().getRequestURI());
		
		return "memo/memoview";
	}
	
	@ResponseBody
	@GetMapping("/ajax/memo/delete/{id}")
	public AjaxResponse deleteMemo(@PathVariable String id) {
		return new AjaxResponse().append("result", memoService.deleteOneMemo(id));
	}
	
	@ResponseBody
	@GetMapping("/ajax/memo/delete/massive")
	public AjaxResponse doDeleteMassive(@RequestParam("memoIds[]") List<String> memoIds) {
		
		boolean deleteResult = this.memoService.deleteManyMemo(memoIds);
		
		return new AjaxResponse().append("result", deleteResult);
		
	}
	
	
	@ResponseBody
	@GetMapping("/ajax/memo/save/{id}")
	public AjaxResponse saveMemo(@PathVariable String id,@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		MemoVO memoVO = new MemoVO();
		
		memoVO.setEmpId(employeeVO.getEmpId());
		memoVO.setMemoId(id);
		
		logger.info("MemoVO Content: {}", memoVO.getEmpId()); 
		logger.info("MemoVO Content: {}", memoVO.getMemoId()); 
		return new AjaxResponse().append("result", memoService.saveOneMemo(memoVO));
	}
	
	@ResponseBody
	@GetMapping("/ajax/memo/status/{id}")
    public AjaxResponse getMemoStatus(@PathVariable String id) {
		
		MemoVO memoVO = memoService.findMemoById(id);
		logger.info(memoVO.getReadYn());
		return new AjaxResponse().append("result", memoVO);
            
    }
	
}
