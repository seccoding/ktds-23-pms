package com.ktdsuniversity.edu.pms.product.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.product.vo.ProductListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;
import com.ktdsuniversity.edu.pms.product.vo.SearchProductVO;

public interface ProductService {

	/**
	 * 비픔 목록과 비품 수를 모두 조회
	 * @return
	 */
	public ProductListVO getAllProduct();
	
	public ProductListVO searchAllProduct(SearchProductVO searchProductVO);

	/**
	 * 입력한 비품 정보들을 새로 추가
	 * @param productList
	 * @return
	 */
	public int createNewProduct(ProductListVO productList);

	public ProductVO getOneProduct(String id);

	public boolean updateOneProduct(String prdtId);
	
	public boolean addProductCount(ProductManagementVO productManagementVO);

	public boolean deleteOneProduct(String prdtId);

	public boolean modifyProduct(ProductVO productVO);
	
	/**
	 * 모든 비품 목록을 중복없이 조회
	 */
	public ProductListVO getAllProductCategory();

	
	/**
	 * 선택된 비품명으로 해당 비품의 재고수를 얻기 위함
	 * @param prdtName 선택된 비품명
	 * @return 선택된 비품의 정보들
	 */
	public ProductVO getOneSelectedProduct(String prdtName);

	/**
	 * 새로운 prdtId 생성
	 * @return
	 */
	public String selectNewPrdtId();

	

}
