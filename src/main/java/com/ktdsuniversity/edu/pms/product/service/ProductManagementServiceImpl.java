package com.ktdsuniversity.edu.pms.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.product.dao.ProductManagementDao;
import com.ktdsuniversity.edu.pms.product.vo.ProductListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;

@Service
public class ProductManagementServiceImpl implements ProductManagementService{
	
	@Autowired
	private ProductManagementDao productManagementDao;

	@Override
	public ProductManagementListVO getAllProductdetail(ProductManagementVO productManagementVO) {
		int productManagementCount = this.productManagementDao.getProductManagementCount(productManagementVO);
		
		productManagementVO.setPageCount(productManagementCount);
		
		List<ProductManagementVO> productManagementList = this.productManagementDao.getAllProductManagement(productManagementVO);
		
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

}
