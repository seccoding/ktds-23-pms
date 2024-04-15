package com.ktdsuniversity.edu.pms.product.service;

import com.ktdsuniversity.edu.pms.product.vo.ProductManagementListVO;

public interface ProductManagementService {

	public ProductManagementListVO getAllProductdetail();

	public ProductManagementListVO getFilteringProductdetail(String id);

}
