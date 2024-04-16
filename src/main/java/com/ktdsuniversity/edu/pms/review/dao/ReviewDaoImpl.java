package com.ktdsuniversity.edu.pms.review.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;

@Repository
public class ReviewDaoImpl extends SqlSessionDaoSupport implements ReviewDao{

	@Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
	
	@Override
	public List<ReviewVO> selectAllReview() {
		return getSqlSession().selectList(ReviewDao.NAME_SPACE + ".selectAllReview");
	}

	@Override
	public List<ReviewVO> viewReviewCntnt() {
		return getSqlSession().selectList(ReviewDao.NAME_SPACE + ".viewReviewCntnt");
	}
	
	@Override
	public ReviewVO getOneReview(String rvId) {
		return getSqlSession().selectOne(ReviewDao.NAME_SPACE + ".seleceOneReview", rvId);
	}
	
	@Override
	public int insertNewReviewQuestion(ReviewVO reviewVO) {
		return getSqlSession().insert(ReviewDao.NAME_SPACE + ".insertNewReviewQuestion", reviewVO);
	}
	
	@Override
	public int insertNewReview(ReviewVO reviewVO) {
		return getSqlSession().insert(ReviewDao.NAME_SPACE + ".insertNewReview", reviewVO);
	}
	
	@Override
	public int updateOneReview(ReviewVO reviewVO) {
		return getSqlSession().update(ReviewDao.NAME_SPACE + ".updateOneReview", reviewVO);
	}

	@Override
	public int deleteOneReview(String rvId) {
		return getSqlSession().update(ReviewDao.NAME_SPACE + ".deleteOneReview", rvId);
	}

	

}
