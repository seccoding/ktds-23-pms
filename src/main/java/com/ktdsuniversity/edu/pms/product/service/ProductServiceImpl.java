package com.ktdsuniversity.edu.pms.product.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.borrow.dao.BorrowDao;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowListVO;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.product.dao.ProductDao;
import com.ktdsuniversity.edu.pms.product.dao.ProductManagementDao;
import com.ktdsuniversity.edu.pms.product.vo.ProductListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;
import com.ktdsuniversity.edu.pms.product.vo.SearchProductVO;

@Service
public class ProductServiceImpl implements ProductService{
	
	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductManagementDao productManagementDao;
	
	@Autowired
	private BorrowDao borrowDao;

	@Override
	public ProductListVO getAllProduct() {
		int productCount = this.productDao.getProductAllCount();
		
		List<ProductVO> productList = this.productDao.getAllProduct();
		
		ProductListVO productListVO = new ProductListVO();
		productListVO.setProductCnt(productCount);
		productListVO.setProductList(productList);
		
		return productListVO;
	}
	
	@Override
	public ProductListVO searchAllProduct(SearchProductVO searchProductVO) {
		int productCount = this.productDao.searchProductAllCount(searchProductVO);
		searchProductVO.setPageCount(productCount);
		
		List<ProductVO> productList = this.productDao.searchAllProduct(searchProductVO);
		
		ProductListVO productListVO = new ProductListVO();
		productListVO.setProductCnt(productCount);
		productListVO.setProductList(productList);
		
		return productListVO;
	}

	@Transactional
	@Override
	public int createNewProduct(ProductListVO productList) {
		int insertCount = 0;
		
		for( ProductVO productVO : productList.getProductList()) {
			
			// 추가할 비품ID의 시퀀스 값을 가져온다.
			String prdtId = this.productDao.selectOnePrdtId();
			
			productVO.setPrdtId(prdtId);
			insertCount += this.productDao.insertNewProduct(productVO);
			
			// 수량만큼 해당 비품의 관리 상세목록에 비품을 추가
			for(int i=0; i < productVO.getCurStr(); i++) {
				
				productVO.getProductManagementVO().setPrdtId(prdtId);
				
				this.productManagementDao.addProductManagement(productVO.getProductManagementVO());
			}
		}
		
		return insertCount;
	}

	@Override
	public ProductVO getOneProduct(String id) {
		ProductVO productVO = this.productDao.selectOneProduct(id);
		
		if (productVO == null) {
			throw new PageNotFoundException();
		}
		return productVO;
	}

	@Transactional
	@Override
	public boolean updateOneProduct(String prdtId) {
		
		return productDao.updateOneProduct(prdtId) > 0;
	}
	
	@Transactional
	@Override
	public boolean addProductCount(ProductManagementVO productManagementVO) {
		int count = productManagementVO.getProductVO().getCurStr();
		int successCount = 0;
		for(var i=0; i < count; i++) {
			successCount += productManagementDao.addProductManagement(productManagementVO);
		}
		boolean isSuccessPrdtCntUp = productDao.updateOneProductCount(productManagementVO.getProductVO()) > 0;
		
		return isSuccessPrdtCntUp && successCount == count;
	}

	@Transactional
	@Override
	public boolean deleteOneProduct(String prdtId) {
		return productDao.deleteOneProduct(prdtId) > 0;
	}

	@Transactional
	@Override
	public boolean modifyProduct(ProductVO productVO) {
		return productDao.modifyProduct(productVO) > 0;
	}
	
	@Override
	public ProductListVO getAllProductCategory() {
		List<ProductVO> productList = this.productDao.getAllProductCategory();
		ProductListVO productListVO = new ProductListVO();
		productListVO.setProductList(productList);
		
		return productListVO;
	}

	@Override
	public ProductVO getOneSelectedProduct(String prdtName) {
		return this.productDao.selectOneProductByPrdtName(prdtName);
	}

	@Override
	public String selectNewPrdtId() {
		return this.productDao.selectOnePrdtId();
	}

	@Transactional
	@Override
	public int createNewApplyProduct(BorrowListVO borrowList) {
		int insertCount = 0;
		
		for( BorrowVO borrowVO : borrowList.getBorrowList() ) {
			
			// 비품명으로 비품ID 값을 가져온다.
			String productName = borrowVO.getProductVO().getPrdtName();
			int productCount = borrowVO.getProductVO().getCurStr(); // 신청한 수량
			String prdtIdByprdtName = this.productDao.selectPrdtIdByPrdtName(productName); // 키보드 03번의 비품ID
			
			
			// 비품ID 값으로 비품관리 ID 값들을 가져온다.
			List<String> prdtManageId = this.productDao.selectPrdtMngIdByPrdtId(prdtIdByprdtName); 
			logger.info(prdtManageId.size()+"<<<<<<<<<<<<<<<<<<<<<");
			logger.info(prdtManageId.getFirst()+"<<<<<<<<<<<<<<<<<<<<<");

			
			// 대여 신청자의 ID를 employee ID 로 받아와서 set
			String brrwID = borrowList.getEmployeeVO().getEmpId();
			borrowVO.setBrrwId(brrwID);
			logger.info(">>>>>>>>>>>>>>>>> 대여아이디" + brrwID);
			logger.info(">>>>>>>>>>>>>>>>> 수량" + borrowVO.getProductVO().getCurStr());

			// 신청수량만큼만 비품 대여현황, 비품 대여현황(관리자)에 비품을 추가
			for(int i=0; i < borrowVO.getProductVO().getCurStr(); i++) {
				
				// 추가할 대여이력ID의 시퀀스 값을 가져온다.
				String brrwHistId = this.borrowDao.selectBrrwHistId();
				borrowVO.setBrrwHistId(brrwHistId);
				logger.info(">>>>>>>>>>>>>>>>대여이력아이디" + brrwHistId);
				
				// PRDT_MNG_ID 값 받아온다.
				String productManageId = prdtManageId.get(i);
				// 비품을 대여상태로 설정. BRRW_YN = 'Y' 세팅 
				this.productManagementDao.changeItemBrrwStateY(productManageId);
				
				
				borrowVO.setPrdtMngId(productManageId);
				
				// 대여 이력에 추가
				this.borrowDao.insertNewBorrowHist(borrowVO);
				
				// 대여현황 페이지에 하나씩 비품을 insert 할때마다 수량을 1 감소
				int stock = this.productDao.changeOnePrdtStoredByPrdtId(prdtIdByprdtName);
				System.out.println(stock);
			}
			
			insertCount++;
			
		}
		
		return insertCount;
	}

	@Override
	public ProductListVO getAllProductName() {
		List<ProductVO> productNameList = this.productDao.getAllProductName();
		ProductListVO productListVO = new ProductListVO();
		productListVO.setProductList(productNameList);
		
		return productListVO;
	}

	@Override
	public ProductVO getOneProductStockAndCategory(String productName) {
		
		// 비품명으로 비품ID 값을 가져온다.
		String prdtIdByprdtName = this.productDao.selectPrdtIdByPrdtName(productName);
		
		return this.productDao.getProductStockAndCategory(prdtIdByprdtName);
	}

	@Override
	public boolean getOneExistProduct(String inputName) {
		
		boolean isExist = false;
		int existCount = this.productDao.getOneExistCount(inputName);
		
		if(existCount >= 1) {
			isExist = true;
		}
		
		return isExist;
	}

	@Override
	public ProductListVO searchAllProductNotReturn(SearchProductVO searchProductVO) {
		int productCount = this.productDao.searchProductAllNotReturnCount(searchProductVO);
		searchProductVO.setPageCount(productCount);
		
		List<ProductVO> notReturnproductList = this.productDao.searchAllProductNotReturn(searchProductVO);
		
		ProductListVO notReturnList = new ProductListVO();
		notReturnList.setProductCnt(productCount);
		notReturnList.setProductList(notReturnproductList);
		
		return notReturnList;
	}
	

}
