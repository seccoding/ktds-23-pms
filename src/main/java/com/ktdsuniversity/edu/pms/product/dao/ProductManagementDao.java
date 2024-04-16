package com.ktdsuniversity.edu.pms.product.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;

public interface ProductManagementDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.product.dao.ProductManagementDao";



	public int getProductManagementCount(ProductManagementVO productManagementVO);

	public List<ProductManagementVO> getAllProductManagement(ProductManagementVO productManagementVO);

	public int getFilteringProductManagementCount(String id);

	public List<ProductManagementVO> getFilteringProductManagement(String id);

}
