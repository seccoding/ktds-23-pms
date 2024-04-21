package com.ktdsuniversity.edu.pms.borrow.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.borrow.service.BorrowService;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowListVO;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.product.service.ProductManagementService;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class BorrowController {
	
	@Autowired
	private BorrowService borrowService;
	
	
	@GetMapping("/product/rentalstate")
	public String viewRentalStatePage(Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		BorrowListVO borrowListVO = this.borrowService.getUserRentalState(employeeVO);
		
		model.addAttribute("userRentalState", borrowListVO);
		return "product/rentalstate";
	}
	
	@GetMapping("/product/manage/state")
	public String viewProductManageStatePage(Model model, ProductVO productVO, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		if (employeeVO.getAdmnCode().equals("302")) {
			throw new PageNotFoundException();
		}
		BorrowListVO borrowListVO = this.borrowService.getProductManageState(productVO);
		model.addAttribute("productState", borrowListVO);
		model.addAttribute("productVO", productVO);
		return "product/managestate";
	}
	
	@ResponseBody
	@GetMapping("/ajax/product/rentalstate/return")
	public AjaxResponse returnOneItem(BorrowVO borrowVO) {
		boolean isReturnSuccess = this.borrowService.returnOneItem(borrowVO);
		
		return new AjaxResponse().append("isSuccess", isReturnSuccess).append("next", "/product/rentalstate");
	}
	
	@ResponseBody
	@GetMapping("/ajax/product/rentalstate/selectedreturn")
	public AjaxResponse returnSelectItem(BorrowListVO borrowListVO) {
		int successCount = 0;
		int listSize = borrowListVO.getBorrowList().size();
		for (BorrowVO borrowVO:borrowListVO.getBorrowList()) {
			boolean isReturnSuccess = this.borrowService.returnOneItem(borrowVO);
			if(isReturnSuccess) {
				successCount++;
			}
			
		}
		
		return new AjaxResponse().append("isSuccess", successCount==listSize).append("next", "/product/rentalstate");
	}

}
