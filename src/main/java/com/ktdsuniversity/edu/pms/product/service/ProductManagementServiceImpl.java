package com.ktdsuniversity.edu.pms.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.borrow.dao.BorrowDao;
import com.ktdsuniversity.edu.pms.product.dao.ProductDao;
import com.ktdsuniversity.edu.pms.product.dao.ProductManagementDao;
import com.ktdsuniversity.edu.pms.product.vo.ProductListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;
import com.ktdsuniversity.edu.pms.product.vo.SearchProductVO;

@Service
public class ProductManagementServiceImpl implements ProductManagementService{
	
	@Autowired
	private ProductManagementDao productManagementDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private BorrowDao borrowDao;

	@Override
	public ProductManagementListVO getAllProductdetail() {
		int productManagementCount = this.productManagementDao.getProductManagementCount();
		
		List<ProductManagementVO> productManagementList = this.productManagementDao.getAllProductManagement();
		
		ProductManagementListVO productManagementListVO = new ProductManagementListVO();
		productManagementListVO.setProductManagementCnt(productManagementCount);
		productManagementListVO.setProductManagementList(productManagementList);
		
		return productManagementListVO;
	}
	
	@Override
	public ProductManagementListVO searchAllProductDetail(SearchProductVO searchProductVO) {
		int productManagementCount = this.productManagementDao.searchProductManagementAllCount(searchProductVO);
		searchProductVO.setPageCount(productManagementCount);
		
		List<ProductManagementVO> productManagementList = this.productManagementDao.searchAllProductManagement(searchProductVO);
		
		ProductManagementListVO productManagementListVO = new ProductManagementListVO();
		productManagementListVO.setProductManagementCnt(productManagementCount);
		productManagementListVO.setProductManagementList(productManagementList);
		
		return productManagementListVO;
	}

	@Override
	public ProductManagementListVO getFilteringProductdetail(String id) {
		int productCount = this.productManagementDao.getFilteringProductManagementCount(id);
		
		List<ProductManagementVO> productManagementList = this.productManagementDao.getFilteringProductManagement(id);
		
		ProductManagementListVO productManagementListVO = new ProductManagementListVO();
		productManagementListVO.setProductManagementCnt(productCount);
		productManagementListVO.setProductManagementList(productManagementList);
		
		return productManagementListVO;
	}

	@Transactional
	@Override
	public boolean deleteOneDeteilProduct(String productId) {
		
		return productManagementDao.deleteOneProductManagement(productId) > 0;
	}

	@Override
	public ProductManagementVO getOneProductManagement(String prdtMngId) {
		ProductManagementVO productManagementVO = this.productManagementDao.getOneProductManagement(prdtMngId);
		return productManagementVO;
	}

	@Transactional
	@Override
	public boolean modifyOneProductManagement(ProductManagementVO productManagementVO) {
		int modifySuccessCnt = this.productManagementDao.modifyOneProductManagement(productManagementVO);
		ProductManagementVO getProductMnItem = this.productManagementDao.getOneProductManagement(productManagementVO.getPrdtMngId());
		int willChange = 0;
		int changeCnt = 0;
		
		if(getProductMnItem.getBrrwYn().equals("Y")) {
			willChange++;
			changeCnt = this.borrowDao.changeState(getProductMnItem.getPrdtMngId());			
		}
		return modifySuccessCnt > 0 && willChange==changeCnt;
	}

	@Transactional
	@Override
	public boolean changeOneItemBrrwState(String prdtMngId) {
		int changeStateCnt = this.productManagementDao.changeOneItemBrrwState(prdtMngId);
		String prdtId = this.productManagementDao.getProductId(prdtMngId);
		int changeProductCnt = 0;
		if(changeStateCnt>0) {
			changeProductCnt = this.productDao.changeOneProductCnt(prdtId);
		}
		return changeProductCnt > 0;
	}

	@Transactional
	@Override
	public Boolean isProductCanDel(String prdtId) {
		int delNCount = this.productManagementDao.getDelNCount(prdtId);
		return delNCount == 0;
	}

}
