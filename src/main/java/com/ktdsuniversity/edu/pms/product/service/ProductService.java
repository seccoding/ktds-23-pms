package com.ktdsuniversity.edu.pms.product.service;

import com.ktdsuniversity.edu.pms.product.vo.ProductListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;

public interface ProductService {

	/**
	 * 비픔 목록과 비품 수를 모두 조회
	 * @return
	 */
	public ProductListVO getAllProduct(ProductVO productVO);

	public boolean createNewProduct(ProductVO productVO);

	public ProductVO getOneProduct(String id);

	public boolean updateOneProduct(String prdtId);

	public boolean addProductCount(ProductManagementVO productManagementVO);

	public boolean deleteOneProduct(String prdtId);

	public boolean modifyProduct(ProductVO productVO);
	
	/**
	 * 모든 비품 목록을 중복없이 조회
	 */
	public ProductListVO getAllProductCategory();


	
	

}
