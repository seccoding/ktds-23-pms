package com.ktdsuniversity.edu.pms.product.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;
import com.ktdsuniversity.edu.pms.product.vo.SearchProductVO;

@Repository
public class ProductDaoImpl extends SqlSessionDaoSupport implements ProductDao{
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<ProductVO> getAllProduct() {
		return getSqlSession().selectList(ProductDao.NAME_SPACE + ".getAllProduct");
	}

	@Override
	public int getProductAllCount() {
		return getSqlSession().selectOne(ProductDao.NAME_SPACE + ".getProductAllCount");
	}
	
	@Override
	public List<ProductVO> searchAllProduct(SearchProductVO searchProductVO) {
		return getSqlSession().selectList(ProductDao.NAME_SPACE + ".searchAllProduct", searchProductVO);
	}
	
	@Override
	public int searchProductAllCount(SearchProductVO searchProductVO) {
		return getSqlSession().selectOne(ProductDao.NAME_SPACE + ".searchProductAllCount", searchProductVO);
	}

	@Override
	public int insertNewProduct(ProductVO productVO) {
		return getSqlSession().insert(ProductDao.NAME_SPACE+".insertNewProduct", productVO);
	}

	@Override
	public ProductVO selectOneProduct(String id) {
		return getSqlSession().selectOne(ProductDao.NAME_SPACE+".selectOneProduct", id);
	}

	@Override
	public int updateOneProduct(String prdtId) {
		return getSqlSession().update(ProductDao.NAME_SPACE+".updateOneProduct" ,prdtId);
	}

	@Override
	public int changeOneProductCnt(String prdtId) {
		return getSqlSession().update(ProductDao.NAME_SPACE+".changeOneProductCnt", prdtId);
	}

	@Override
	public int updateOneProductCount(ProductVO productVO) {
		return getSqlSession().update(ProductDao.NAME_SPACE+".updateOneProductCount", productVO);
	}

	@Override
	public int deleteOneProduct(String prdtId) {
		return getSqlSession().update(ProductDao.NAME_SPACE+".deleteOneProduct", prdtId);
	}

	@Override
	public int modifyProduct(ProductVO productVO) {
		return getSqlSession().update(ProductDao.NAME_SPACE+".modifyProduct", productVO);
	}
	
	public List<ProductVO> getAllProductCategory() {
		return getSqlSession().selectList(ProductDao.NAME_SPACE+".getAllProductCategory");
	}

	@Override
	public List<ProductVO> getAllProductList() {
		return getSqlSession().selectList(ProductDao.NAME_SPACE+".getAllProductList");
	}

	@Override
	public ProductVO selectOneProductByPrdtName(String prdtName) {
		return getSqlSession().selectOne(ProductDao.NAME_SPACE+".selectOneProductByPrdtName", prdtName);
	}

	@Override
	public String selectOnePrdtId() {
		return getSqlSession().selectOne(ProductDao.NAME_SPACE+".selectOnePrdtId");
	}
	
	// PSH0422
	@Override
	public int changeOnePrdtStored(String prdtName) {
		return getSqlSession().update(ProductDao.NAME_SPACE + ".changeOnePrdtStored", prdtName);
	}

	// YSH0424
	@Override
	public String selectPrdtIdByPrdtName(String productName) {
		return getSqlSession().selectOne(ProductDao.NAME_SPACE+".selectPrdtIdByPrdtName", productName);
	}

	@Override
	public List<String> selectPrdtMngIdByPrdtId(String prdtIdByprdtName) {
		return getSqlSession().selectList(ProductDao.NAME_SPACE+".selectPrdtMngIdByPrdtId", prdtIdByprdtName);
	}

	@Override
	public List<ProductVO> getAllProductName() {
		return getSqlSession().selectList(ProductDao.NAME_SPACE+".getAllProductName");
	}

	@Override
	public ProductVO getProductStockAndCategory(String prdtIdByprdtName) {
		return getSqlSession().selectOne(ProductDao.NAME_SPACE+".getProductStockAndCategory", prdtIdByprdtName);
	}

	@Override
	public int changeOnePrdtStoredByPrdtId(String prdtIdByprdtName) {
		return getSqlSession().update(ProductDao.NAME_SPACE + ".changeOnePrdtStoredByPrdtId", prdtIdByprdtName);
	}

	@Override
	public int getOneExistCount(String inputName) {
		return getSqlSession().selectOne(ProductDao.NAME_SPACE + ".getOneExistCount", inputName);
	}

	@Override
	public List<ProductVO> searchAllProductNotReturn(SearchProductVO searchProductVO) {
		return getSqlSession().selectList(ProductDao.NAME_SPACE + ".searchAllProductNotReturn", searchProductVO);
	}

	@Override
	public int searchProductAllNotReturnCount(SearchProductVO searchProductVO) {
		return getSqlSession().selectOne(ProductDao.NAME_SPACE + ".searchProductAllNotReturnCount", searchProductVO);
	}
}
