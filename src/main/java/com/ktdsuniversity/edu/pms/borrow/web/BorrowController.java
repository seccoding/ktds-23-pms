package com.ktdsuniversity.edu.pms.borrow.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.borrow.service.BorrowService;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowListVO;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.product.service.ProductManagementService;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class BorrowController {
	
	@Autowired
	private BorrowService borrowService;
	
	@Autowired
	private ProductManagementService productManagementService;
	
	@GetMapping("/product/rentalstate")
	public String viewRentalStatePage(Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		BorrowListVO borrowListVO = this.borrowService.getUserRentalState(employeeVO);
		model.addAttribute("userRentalState", borrowListVO);
		return "product/rentalstate";
	}
	
	@GetMapping("/product/manage/state")
	public String viewProductManageStatePage(Model model, ProductVO productVO) {
		BorrowListVO borrowListVO = this.borrowService.getProductManageState(productVO);
		model.addAttribute("productState", borrowListVO);
		model.addAttribute("productVO", productVO);
		return "product/managestate";
	}
	
	@GetMapping("/ajax/product/rentalstate/return")
	public AjaxResponse returnOneItem(BorrowVO borrowVo) {
		boolean isReturnSuccess = this.borrowService.returnOneItem(borrowVo.getBrrwHistId());
		boolean isProductManageBrrwChange = false;
		if(isReturnSuccess) {
			isProductManageBrrwChange = this.productManagementService.changeOneItemBrrwState(borrowVo.getPrdtMngId());
		}
		return new AjaxResponse().append("isSuccess", isProductManageBrrwChange);
	}

}
