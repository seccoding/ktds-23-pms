package com.ktdsuniversity.edu.pms.product.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;
import com.ktdsuniversity.edu.pms.product.vo.SearchProductVO;

public interface ProductDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.product.dao.ProductDao";

	public List<ProductVO> getAllProduct();

	public int getProductAllCount();
	
	public int searchProductAllCount(SearchProductVO searchProductVO);

	public List<ProductVO> searchAllProduct(SearchProductVO searchProductVO);

	/**
	 * 새로운 비품 추가
	 * @param productVO
	 * @return
	 */
	public int insertNewProduct(ProductVO productVO);

	public ProductVO selectOneProduct(String id);

	public int updateOneProduct(String prdtId);

	public int changeOneProductCnt(String prdtId);
	
	public int updateOneProductCount(ProductVO productVO);

	public int deleteOneProduct(String prdtId);

	public int modifyProduct(ProductVO productVO);
	
	/**
	 * 모든 비품 목록을 중복없이 조회
	 * 쿼리에서 DISTINCT를 추가해 중복없이 조회
	 */
	public List<ProductVO> getAllProductCategory();

	public List<ProductVO> getAllProductList();

	/**
	 * 해당 비품명을 가진 비품의 모든 정보를 조회
	 * @param prdtName 조회할 비품
	 * @return 비품의 정보들 
	 */
	public ProductVO selectOneProductByPrdtName(String prdtName);

	/**
	 * prdtId 값 조회
	 * @return
	 */
	public String selectOnePrdtId();
	
	// PSH0422
	public int changeOnePrdtStored(String prdtName);

	
	// YSH0424
	
	/**
	 * 비품명으로 수량이 0이 아닌 비품ID 조회, 비품명이 동일할 시에 생성된지 오래된 비품의 정보를 가져옴
	 * @return
	 */
	public String selectPrdtIdByPrdtName(String productName);

	/**
	 * 비품ID로 비품관리 ID 조회
	 * @param prdtIdByprdtName
	 * @return
	 */
	public List<String> selectPrdtMngIdByPrdtId(String prdtIdByprdtName);

	/**
	 * 모든 비품명을 중복없이 조회
	 * @return
	 */
	public List<ProductVO> getAllProductName();

	/**
	 * 비품ID 값으로 해당 비품의 수량과 카테고리를 조회
	 * @param namevalue
	 * @return
	 */
	public ProductVO getProductStockAndCategory(String prdtIdByprdtName);

	/**
	 * 비품 ID 값으로 해당 비품의 수량을 변경
	 * @param prdtIdByprdtName
	 */
	public int changeOnePrdtStoredByPrdtId(String prdtIdByprdtName);

	/**
	 * 입력한 비품명이 테이블 내 존재하는지 확인
	 * 개수가 1개 이상이면 존재하는 것으로 판별 
	 * @param inputName
	 * @return
	 */
	public int getOneExistCount(String inputName);

	public List<ProductVO> searchAllProductNotReturn(SearchProductVO searchProductVO);

	public int searchProductAllNotReturnCount(SearchProductVO searchProductVO);

	

}
