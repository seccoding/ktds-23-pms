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
	
	/**
	 * 모든 비품 목록을 중복없이 조회
	 * 쿼리에서 DISTINCT를 추가해 중복없이 만들고
	 * 나중에 forEach로 값을 받아올 때 카페고리 값만 받아오도록 해주기 위해 1 컬럼을 추가
	 */
	public List<ProductVO> getAllProductCategory();

	public List<ProductVO> getAllProductList();

}
