package com.ktdsuniversity.edu.pms.product.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.product.vo.ProductVO;

public interface ProductDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.product.dao.ProductDao";

	public List<ProductVO> getAllProduct(ProductVO productVO);

	public int getProductAllCount(ProductVO productVO);

	public int insertNewProduct(ProductVO productVO);

	public ProductVO selectOneProduct(String id);

	public int updateOneProduct(String prdtId);

	public int changeOneProductCnt(String prdtId);

	public int updateOneProductCount(ProductVO productVO);

	public int deleteOneProduct(String prdtId);

	public int modifyProduct(ProductVO productVO);

}
