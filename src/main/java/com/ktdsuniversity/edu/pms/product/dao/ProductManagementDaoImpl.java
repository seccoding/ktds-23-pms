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
	public int getProductManagementCount() {
		return getSqlSession().selectOne(ProductManagementDao.NAME_SPACE+".getProductManagementCount");
	}

	@Override
	public List<ProductManagementVO> getAllProductManagement() {
		return getSqlSession().selectList(ProductManagementDao.NAME_SPACE+".getAllProductManagement");
	}


	@Override
	public int getFilteringProductManagementCount(String id) {
		return getSqlSession().selectOne(ProductManagementDao.NAME_SPACE+".getFilteringProductManagementCount", id);
	}


	@Override
	public List<ProductManagementVO> getFilteringProductManagement(String id) {
		return getSqlSession().selectList(ProductManagementDao.NAME_SPACE+".getFilteringProductManagement", id);
	}

}
