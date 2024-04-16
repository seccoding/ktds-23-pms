package com.ktdsuniversity.edu.pms.borrow.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.borrow.service.BorrowService;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

@Controller
public class BorrowController {
	
	@Autowired
	private BorrowService borrowService;
	
	@GetMapping("/product/rentalstate")
	public String viewRentalStatePage(Model model, @SessionAttribute("") EmployeeVO employeeVO) {
		BorrowListVO borrowListVO = this.borrowService.getUserRentalState(employeeVO);
		model.addAttribute("userRentalState", borrowListVO);
		return "product/rentalstate";
	}
	
	@GetMapping("/product/manage/state")
	public String viewProductManageStatePage(Model model) {
		BorrowListVO borrowListVO = this.borrowService.getProductManageState();
		model.addAttribute("productState", borrowListVO);
		return "product/managestate";
	}

}
