package com.ktdsuniversity.edu.pms.product.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;

@Repository
public class ProductManagementDaoImpl extends SqlSessionDaoSupport implements ProductManagementDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}


	@Override
	public int getProductManagementCount(ProductManagementVO productManagementVO) {
		return getSqlSession().selectOne(ProductManagementDao.NAME_SPACE+".getProductManagementCount", productManagementVO);
	}

	@Override
	public List<ProductManagementVO> getAllProductManagement(ProductManagementVO productManagementVO) {
		return getSqlSession().selectList(ProductManagementDao.NAME_SPACE+".getAllProductManagement", productManagementVO);
	}


	@Override
	public int getFilteringProductManagementCount(String id) {
		return getSqlSession().selectOne(ProductManagementDao.NAME_SPACE+".getFilteringProductManagementCount", id);
	}


	@Override
	public List<ProductManagementVO> getFilteringProductManagement(String id) {
		return getSqlSession().selectList(ProductManagementDao.NAME_SPACE+".getFilteringProductManagement", id);
	}


	@Override
	public int deleteOneProductManagement(String productId) {
		return getSqlSession().update(ProductManagementDao.NAME_SPACE+".deleteOneProductManagement", productId);
	}


	@Override
	public ProductManagementVO getOneProductManagement(String productId) {
		
		return getSqlSession().selectOne(ProductManagementDao.NAME_SPACE+".getOneProductManagement",productId);
	}


	@Override
	public int modifyOneProductManagement(ProductManagementVO productManagementVO) {
		return getSqlSession().update(ProductManagementDao.NAME_SPACE+".modifyOneProductManagement", productManagementVO);
	}


	@Override
	public int changeOneItemBrrwState(String prdtMngId) {
		return getSqlSession().update(ProductManagementDao.NAME_SPACE+".changeOneItemBrrwState", prdtMngId);
	}


	@Override
	public String getProductId(String prdtMngId) {
		return getSqlSession().selectOne(ProductManagementDao.NAME_SPACE+".getProductId", prdtMngId);
	}

}
