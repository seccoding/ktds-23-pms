package com.ktdsuniversity.edu.pms.product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.product.service.ProductManagementService;
import com.ktdsuniversity.edu.pms.product.service.ProductService;
import com.ktdsuniversity.edu.pms.product.vo.ProductListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductManagementService productManagementService;
	
	
	@GetMapping("/product/list")
	public String viewProductListPage(Model model, ProductVO productVO) {
		ProductListVO productListVO = this.productService.getAllProduct(productVO);
		model.addAttribute("productList", productListVO);
		model.addAttribute("productVO", productVO);
		return "product/list";
	}
	
	
	@GetMapping("/product/manage/list")
	public String viewProductManageListPage(Model model, ProductVO productVO) {
		ProductListVO productListVO = this.productService.getAllProduct(productVO);
		model.addAttribute("productList", productListVO);
		model.addAttribute("productVO", productVO);
		return "product/managelist";
	}
	
	
	@GetMapping("/product/manage/detail")
	public String viewProductManageDetailPage(Model model, ProductManagementVO productManagementVO) {
		ProductManagementListVO productManagementListVO = this.productManagementService.getAllProductdetail(productManagementVO);
		model.addAttribute("productManagementList", productManagementListVO);
		model.addAttribute("productVO", productManagementVO);
		return "product/managedetail";
	}
	
	
	
	@GetMapping("/product/manage/view")
	public String viewProductManageViewPage(@RequestParam String prdtId, Model model) {
		ProductVO productVO = this.productService.getOneProduct(prdtId);
		ProductManagementListVO productDetailListVO= this.productManagementService.getFilteringProductdetail(prdtId);
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
	
	@ResponseBody
	@GetMapping("/ajax/product/manage/view/delete/{prdtMngId}")
	public AjaxResponse viewDeleteDatailProductPage(@PathVariable String prdtMngId) {
		boolean isDeleteSuccess = this.productManagementService.deleteOneDeteilProduct(prdtMngId);
		boolean isUpdateCnt = false;
		if (isDeleteSuccess) {
			ProductManagementVO productManagementVO = this.productManagementService.getOneProductManagement(prdtMngId);
			isUpdateCnt = this.productService.updateOneProduct(productManagementVO.getPrdtId());
		}
		return new AjaxResponse().append("result", isUpdateCnt);
	}
	
	@ResponseBody
	@GetMapping("/ajax/product/manage/view/modify/{prdtMngId}")
	public AjaxResponse viewModifyDetailProduct(@PathVariable String prdtMngId) {
		ProductManagementVO productManagementVO = this.productManagementService.getOneProductManagement(prdtMngId);
		return new AjaxResponse().append("product", productManagementVO);
	}
	
	@ResponseBody
	@PostMapping("/ajax/product/manage/view/modify")
	public AjaxResponse doModifyDetailProduct(ProductManagementVO productManagementVO) {
		System.out.println(productManagementVO.getBuyDt());
		System.out.println(productManagementVO.getPrdtId());
		System.out.println(productManagementVO.getPrdtMngId());
		System.out.println(productManagementVO.getPrdtPrice());
		boolean isModifySuccess = this.productManagementService.modifyOneProductManagement(productManagementVO);
		return new AjaxResponse().append("result", isModifySuccess).append("next", "/product/manage/view?prdtId="+productManagementVO.getPrdtId()).append("detailUrl", "/product/manage/detail");
	}
	
}
