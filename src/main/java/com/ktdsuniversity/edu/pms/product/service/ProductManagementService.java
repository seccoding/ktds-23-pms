package com.ktdsuniversity.edu.pms.product.service;

import com.ktdsuniversity.edu.pms.product.vo.ProductListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;

public interface ProductManagementService {

	public ProductManagementListVO getAllProductdetail(ProductManagementVO productManagementVO);

	public ProductManagementListVO getFilteringProductdetail(String id);

	public boolean deleteOneDeteilProduct(String productId);

	public ProductManagementVO getOneProductManagement(String productId);

	public boolean modifyOneProductManagement(ProductManagementVO productManagementVO);

	public boolean changeOneItemBrrwState(String prdtMngId);

	public int addSomeProductManagement(ProductListVO productList, String prdtId);

	public Boolean isProductCanDel(String prdtId);

}
