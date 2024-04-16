package com.ktdsuniversity.edu.pms.product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.product.service.ProductManagementService;
import com.ktdsuniversity.edu.pms.product.service.ProductService;
import com.ktdsuniversity.edu.pms.product.vo.ProductListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductManagementService productManagementService;
	
	
	@GetMapping("/product/list")
	public String viewProductListPage(Model model) {
		ProductListVO productListVO = this.productService.getAllProduct();
		model.addAttribute("productList", productListVO);
		return "product/list";
	}
	
	
	
	@GetMapping("/product/manage/list")
	public String viewProductManageListPage(Model model) {
		ProductListVO productListVO = this.productService.getAllProduct();
		model.addAttribute("productList", productListVO);
		return "product/managelist";
	}
	
	
	@GetMapping("/product/manage/detail")
	public String viewProductManageDetailPage(Model model) {
		ProductManagementListVO productManagementListVO = this.productManagementService.getAllProductdetail();
		model.addAttribute("productManagementList", productManagementListVO);
		return "product/managedetail";
	}
	
	
	
	@GetMapping("/product/manage/view")
	public String viewProductManageViewPage(@RequestParam String id, Model model) {
		ProductVO productVO = this.productService.getOneProduct(id);
		ProductManagementListVO productDetailListVO= this.productManagementService.getFilteringProductdetail(id);
		model.addAttribute("productVO", productVO);
		model.addAttribute("productDetailList", productDetailListVO);
		
		return "product/manageview";
	}
	
	
	@GetMapping("/product/manage/add")
	public String viewProductManageAddPage() {
		return "product/manageadd";
	}
	
	@ResponseBody
	@PostMapping("/ajax/product/manage/add")
	public AjaxResponse doProductManageAdd(ProductVO productVO) {
		 boolean isCreateSuccess = this.productService.createNewProduct(productVO);
		return new AjaxResponse().append("result", isCreateSuccess).append("next", "/product/manage/list");
	}
	
	
	
}
