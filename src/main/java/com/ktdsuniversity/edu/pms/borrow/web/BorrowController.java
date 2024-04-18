package com.ktdsuniversity.edu.pms.borrow.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@ResponseBody
	@GetMapping("/ajax/product/rentalstate/return")
	public AjaxResponse returnOneItem(BorrowVO borrowVO) {
		boolean isReturnSuccess = this.borrowService.returnOneItem(borrowVO.getBrrwHistId());
		boolean isProductManageBrrwChange = false;
		if(isReturnSuccess) {
			isProductManageBrrwChange = this.productManagementService.changeOneItemBrrwState(borrowVO.getPrdtMngId());
		}
		return new AjaxResponse().append("isSuccess", isProductManageBrrwChange);
	}
	
	@ResponseBody
	@GetMapping("/ajax/product/rentalstate/selectedreturn")
	public AjaxResponse returnSelectItem(BorrowListVO borrowListVO) {
		int successCount = 0;
		int listSize = borrowListVO.getBorrowList().size();
		for (BorrowVO borrowVO:borrowListVO.getBorrowList()) {
			System.out.println(borrowVO.getBrrwHistId());
			boolean isReturnSuccess = this.borrowService.returnOneItem(borrowVO.getBrrwHistId());
			System.out.println("!!!!!!!!controller!!!!!!!!"+isReturnSuccess);
			boolean isProductManageBrrwChange = false;
			if(isReturnSuccess) {
				isProductManageBrrwChange = this.productManagementService.changeOneItemBrrwState(borrowVO.getPrdtMngId());
			}
			if(isProductManageBrrwChange) {
				successCount++;
			}
			
		}
		System.out.println("!!!!!!!!controller!!!!!!!!"+successCount);
		System.out.println("!!!!!!!!controller!!!!!!!!"+listSize);
		
		return new AjaxResponse().append("isSuccess", successCount==listSize).append("next", "/product/rentalstate");
	}

}
