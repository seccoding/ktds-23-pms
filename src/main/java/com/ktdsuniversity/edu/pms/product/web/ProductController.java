package com.ktdsuniversity.edu.pms.product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.borrow.vo.BorrowListVO;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.AccessDeniedException;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.product.service.ProductManagementService;
import com.ktdsuniversity.edu.pms.product.service.ProductService;
import com.ktdsuniversity.edu.pms.product.vo.ProductListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;
import com.ktdsuniversity.edu.pms.product.vo.SearchProductVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductManagementService productManagementService;
	
	
	@GetMapping("/product/list")
	public String viewProductListPage(Model model, ProductVO productVO, SearchProductVO searchProductVO) {
//		ProductListVO productListVO = this.productService.getAllProduct();
		
		boolean isCheck = false;
		if(searchProductVO.getIsCheck() != null) {
			isCheck = searchProductVO.getIsCheck();
		}
		
		ProductListVO productListVO = this.productService.searchAllProduct(searchProductVO);
		ProductListVO notReturnListVO = this.productService.searchAllProductNotReturn(searchProductVO);
		
		model.addAttribute("productList", isCheck ? notReturnListVO : productListVO);
		model.addAttribute("isCheck", isCheck);
		model.addAttribute("productVO", productVO);
		return "product/list";
	}
	
	
	@GetMapping("/product/apply")
	public String viewProductApplyPage(Model model, ProductVO productVO, SearchProductVO searchProductVO) {
		// 비품명 선택
		ProductListVO productListVO = this.productService.searchAllProduct(searchProductVO);
		model.addAttribute("productListVO", productListVO);
		
		// 카테고리 선택
		ProductListVO categoryList = this.productService.getAllProductCategory();
		model.addAttribute("categoryList", categoryList);
		
		// 비품명 선택
		ProductListVO productNameList = this.productService.getAllProductName();
		model.addAttribute("nameList", productNameList);
		
		return "product/apply";
	}
	
	
	@ResponseBody
	@GetMapping("/ajax/product/apply/{namevalue}")
	public AjaxResponse viewProductApply(@RequestParam String productName, @PathVariable String namevalue) {
		ProductVO oneProductStockAndCategory = this.productService.getOneProductStockAndCategory(productName);
		System.out.println("**********************" + oneProductStockAndCategory.getCurStr() + "**********************");
		System.out.println("**********************" + oneProductStockAndCategory.getPrdtCtgr() + "**********************");
		
		return new AjaxResponse().append("stockAndCategory", oneProductStockAndCategory);
	}
	
	
	@ResponseBody
	@PostMapping("/ajax/product/apply")
	public AjaxResponse doProductApply(BorrowListVO borrowList
									, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
//		ProductVO oneProduct = this.productService.getOneSelectedProduct(prdtName);
		
		borrowList.setEmployeeVO(employeeVO);
		
		int isApplySuccess = this.productService.createNewApplyProduct(borrowList);
		
		if(isApplySuccess != borrowList.getBorrowList().size()) {
			throw new PageNotFoundException();
		}
		
		
		return new AjaxResponse().append("next", "/product/list");
	}
	
	
	@GetMapping("/product/manage/list")
	public String viewProductManageListPage(Model model, ProductVO productVO, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO
								, SearchProductVO searchProductVO) {
		if (employeeVO.getAdmnCode().equals("302")) {
			throw new AccessDeniedException();
		}
		
		boolean isCheck = false;
		if(searchProductVO.getIsCheck() != null) {
			isCheck = searchProductVO.getIsCheck();
		}
		
		ProductListVO productListVO = this.productService.searchAllProduct(searchProductVO);
		ProductListVO notReturnListVO = this.productService.searchAllProductNotReturn(searchProductVO);
		
		model.addAttribute("productList", isCheck ? notReturnListVO : productListVO);
		model.addAttribute("isCheck", isCheck);
		model.addAttribute("productVO", productVO);
		return "product/managelist";
	}
	
	@ResponseBody
	@GetMapping("/product/manage/list/iscandel")
	public AjaxResponse isproductCanDel(@RequestParam String prdtId) {
		Boolean canDel = this.productManagementService.isProductCanDel(prdtId);
		return new AjaxResponse().append("canDel", canDel);
	}
	
	@ResponseBody
	@GetMapping("/product/manage/list/del")
	public AjaxResponse delProduct(@RequestParam String prdtId) {
		boolean isDelSuccess =  productService.deleteOneProduct(prdtId);
		return new AjaxResponse().append("result", isDelSuccess).append("next", "/product/manage/list");
	}
	
	@GetMapping("/product/manage/detail")
	public String viewProductManageDetailPage(Model model, ProductManagementVO productManagementVO, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO
									, SearchProductVO searchProductVO) {
		if (employeeVO.getAdmnCode().equals("302")) {
			throw new AccessDeniedException();
		}
//		ProductManagementListVO productManagementListVO = this.productManagementService.getAllProductdetail();
		ProductManagementListVO productManagementListVO = this.productManagementService.searchAllProductDetail(searchProductVO);
		model.addAttribute("productManagementList", productManagementListVO);
		model.addAttribute("productVO", productManagementVO);
		return "product/managedetail";
	}
	
	
	
	@GetMapping("/product/manage/view")
	public String viewProductManageViewPage(@RequestParam String prdtId, Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		if (employeeVO.getAdmnCode().equals("302")) {
			throw new AccessDeniedException();
		}
		ProductVO productVO = this.productService.getOneProduct(prdtId);
		ProductManagementListVO productDetailListVO= this.productManagementService.getFilteringProductdetail(prdtId);
		model.addAttribute("productVO", productVO);
		model.addAttribute("productDetailList", productDetailListVO);
		
		return "product/manageview";
	}
	
	
	@GetMapping("/product/manage/add")
	public String viewProductManageAddPage(Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, ProductVO productVO) {
		if (employeeVO.getAdmnCode().equals("302")) {
			throw new AccessDeniedException();
		}
		
		// 카테고리 선택
		ProductListVO categoryList = this.productService.getAllProductCategory();
		model.addAttribute("categoryList", categoryList);
		
		return "product/manageadd";
	}
	
	
	@ResponseBody
	@GetMapping("ajax/product/manage/add/{inputname}")
	public AjaxResponse viewProductAdd(@RequestParam String inputName, @PathVariable String inputname) {
		boolean isExistResult = this.productService.getOneExistProduct(inputName);
		
		System.out.println("~~~~~~~~~~~~" + isExistResult + "~~~~~~~~~~~~~~~");
		
		return new AjaxResponse().append("result", isExistResult);
	}
	
	@ResponseBody
	@PostMapping("/ajax/product/manage/add")
	public AjaxResponse doProductManageAdd(ProductListVO productList) {
		
//		String prdtId = this.productService.selectNewPrdtId();
		
		
		int isCreateSuccess = this.productService.createNewProduct(productList);
		
		if(isCreateSuccess != productList.getProductList().size()) {
			throw new PageNotFoundException();
		}
		
		
		
		return new AjaxResponse().append("result1", isCreateSuccess).append("next", "/product/manage/list");
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
		
		System.out.println("~~~~~~~~~~~~~~~~~" + productManagementVO.getLostYn() + "~~~~~~~~~~~~~~~~~~~~~~");
		
		
		boolean isModifySuccess = this.productManagementService.modifyOneProductManagement(productManagementVO);
		return new AjaxResponse().append("result", isModifySuccess).append("next", "/product/manage/view?prdtId="+productManagementVO.getPrdtId()).append("detailUrl", "/product/manage/detail");
	}
	
	@ResponseBody
	@PostMapping("/ajax/product/manage/list/add")
	public AjaxResponse addProductCount(ProductManagementVO productManagementVO) {
		
		boolean isCountAddSuccess = this.productService.addProductCount(productManagementVO);
		
		return new AjaxResponse().append("result", isCountAddSuccess).append("next", "/product/manage/list");
	}
	
	@ResponseBody
	@PostMapping("/ajax/product/manage/view/modifymain")
	public AjaxResponse modifyProduct(ProductVO productVO) {
		boolean isModifySuccess = this.productService.modifyProduct(productVO);
		return new AjaxResponse().append("result", isModifySuccess).append("next", "/product/manage/view?prdtId="+productVO.getPrdtId());
	}
	
	
}
