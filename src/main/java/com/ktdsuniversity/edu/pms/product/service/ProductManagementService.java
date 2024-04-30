package com.ktdsuniversity.edu.pms.product.service;

import com.ktdsuniversity.edu.pms.product.vo.ProductListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;
import com.ktdsuniversity.edu.pms.product.vo.SearchProductVO;

public interface ProductManagementService {

	public ProductManagementListVO getAllProductdetail();

	public ProductManagementListVO searchAllProductDetail(SearchProductVO searchProductVO);
	
	public ProductManagementListVO getFilteringProductdetail(String id);

	public boolean deleteOneDeteilProduct(String productId);

	public ProductManagementVO getOneProductManagement(String prdtMngId);

	public boolean modifyOneProductManagement(ProductManagementVO productManagementVO);

	public boolean changeOneItemBrrwState(String prdtMngId);

	public Boolean isProductCanDel(String prdtId);


}
