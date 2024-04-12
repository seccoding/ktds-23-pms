package com.ktdsuniversity.edu.pms.product.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
	
	
	@GetMapping("/product/list")
	public String viewProductListPage() {
		
		return "product/list";
	}
	
	
	@GetMapping("/product/rentalsate")
	public String viewRentalStatePage() {
		
		return "product/rentalstate";
	}
	
	
	@GetMapping("/product/manage/list")
	public String viewProductManageListPage() {
		
		return "product/managelist";
	}
	
	
	@GetMapping("/product/manage/detail")
	public String viewProductManageDetailPage() {
		
		return "product/managedetail";
	}
	
	
	@GetMapping("/product/manage/state")
	public String viewProductManageStatePage() {
		
		return "product/managestate";
	}
	
	
	@GetMapping("/product/manage/view")
	public String viewProductManageViewPage() {
		
		return "product/manageview";
	}
	
	
	@GetMapping("/product/manage/add")
	public String viewProductManageAddPage() {
		
		return "product/manageadd";
	}
	
	
	@PostMapping("/product/manage/add")
	public String doProductManageAdd() {
		
		return "redirect:/product/manage/list";
	}
	
	
	
}
