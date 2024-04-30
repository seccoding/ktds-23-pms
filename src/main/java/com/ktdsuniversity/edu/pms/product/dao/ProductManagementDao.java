package com.ktdsuniversity.edu.pms.product.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;
import com.ktdsuniversity.edu.pms.product.vo.SearchProductVO;

public interface ProductManagementDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.product.dao.ProductManagementDao";



	public int getProductManagementCount();

	public List<ProductManagementVO> getAllProductManagement();
	
	public int searchProductManagementAllCount(SearchProductVO searchProductVO);

	public List<ProductManagementVO> searchAllProductManagement(SearchProductVO searchProductVO);

	public int getFilteringProductManagementCount(String id);

	public List<ProductManagementVO> getFilteringProductManagement(String id);

	public int deleteOneProductManagement(String productId);

	public ProductManagementVO getOneProductManagement(String prdtMngId);

	public int modifyOneProductManagement(ProductManagementVO productManagementVO);

	public int changeOneItemBrrwState(String prdtMngId);

	public String getProductId(String prdtMngId);

	public int addProductManagement(ProductManagementVO productManagementVO);

	public int getDelNCount(String prdtId);
	
	// PHS0422
    public int unusablePrdtByAppr(String apprId);

	public String getNewPrdtMngIdForBorrow(String prdtName);

	public int changeItemBrrwStateY(String prdtMngId);
	
	public ProductManagementVO selectPrdtForNewAppr(String prdtMngId);
	

}
