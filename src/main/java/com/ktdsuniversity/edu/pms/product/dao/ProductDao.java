package com.ktdsuniversity.edu.pms.product.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.product.vo.ProductVO;

public interface ProductDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.product.dao.ProductDao";

	public List<ProductVO> getAllProduct();

	public int getProductAllCount();

	public int insertNewProduct(ProductVO productVO);

	public ProductVO selectOneProduct(String id);

}
