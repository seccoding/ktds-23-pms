package com.ktdsuniversity.edu.pms.product.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;
import com.ktdsuniversity.edu.pms.product.vo.SearchProductVO;

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
	public int searchProductManagementAllCount(SearchProductVO searchProductVO) {
		return getSqlSession().selectOne(ProductManagementDao.NAME_SPACE+".searchProductManagementAllCount", searchProductVO);
	}
	
	@Override
	public List<ProductManagementVO> searchAllProductManagement(SearchProductVO searchProductVO) {
		return getSqlSession().selectList(ProductManagementDao.NAME_SPACE+".searchAllProductManagement", searchProductVO);
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
	public ProductManagementVO getOneProductManagement(String prdtMngId) {
		
		return getSqlSession().selectOne(ProductManagementDao.NAME_SPACE+".getOneProductManagement",prdtMngId);
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


	@Override
	public int addProductManagement(ProductManagementVO productManagementVO) {
		return getSqlSession().insert(ProductManagementDao.NAME_SPACE+".addProductManagement", productManagementVO);
	}


	@Override
	public int getDelNCount(String prdtId) {
		return getSqlSession().selectOne(ProductManagementDao.NAME_SPACE+".getDelNCount", prdtId);
	}
	
	// PSH0422
	@Override
	public int unusablePrdtByAppr(String apprId) {
		return getSqlSession().update(ProductManagementDao.NAME_SPACE + ".unusablePrdtByAppr", apprId);
	}

	@Override
	public String getNewPrdtMngIdForBorrow(String prdtName) {
		return getSqlSession().selectOne(ProductManagementDao.NAME_SPACE + ".getNewPrdtMngIdForBorrow", prdtName);
	}

	@Override
	public int changeItemBrrwStateY(String prdtMngId) {
		return getSqlSession().update(ProductManagementDao.NAME_SPACE + ".changeItemBrrwStateY", prdtMngId);
	}


	@Override
	public ProductManagementVO selectPrdtForNewAppr(String prdtMngId) {
		return getSqlSession().selectOne(ProductManagementDao.NAME_SPACE + ".selectPrdtForNewAppr", prdtMngId);
	}

}
