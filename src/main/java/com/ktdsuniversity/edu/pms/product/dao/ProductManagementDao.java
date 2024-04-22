package com.ktdsuniversity.edu.pms.product.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;

public interface ProductManagementDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.product.dao.ProductManagementDao";



	public int getProductManagementCount(ProductManagementVO productManagementVO);

	public List<ProductManagementVO> getAllProductManagement(ProductManagementVO productManagementVO);

	public int getFilteringProductManagementCount(String id);

	public List<ProductManagementVO> getFilteringProductManagement(String id);

	public int deleteOneProductManagement(String productId);

	public ProductManagementVO getOneProductManagement(String productId);

	public int modifyOneProductManagement(ProductManagementVO productManagementVO);

	public int changeOneItemBrrwState(String prdtMngId);

	public String getProductId(String prdtMngId);

	public int addProductManagement(ProductVO productVO);

	public int getDelNCount(String prdtId);

}
