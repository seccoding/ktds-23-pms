package com.ktdsuniversity.edu.pms.review.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;
import com.ktdsuniversity.edu.pms.review.vo.SearchReviewVO;

@Repository
public class ReviewDaoImpl extends SqlSessionDaoSupport implements ReviewDao{

	@Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
	
	/**
	 * 후기관리 페이지에서 보여줄 모든 정보를 표로담아 보여주기 위함
	 */
	/*
	 * @Override public List<ReviewVO> selectAllReview() { return
	 * getSqlSession().selectList(ReviewDao.NAME_SPACE + ".selectAllReview"); }
	 */
	
	@Override
	public List<ReviewVO> searchReview(SearchReviewVO searchReviewVO) {
		return getSqlSession().selectList(ReviewDao.NAME_SPACE + ".searchReview", searchReviewVO);
	}
	
	
	
	/**
	 * 후기 작성된 내용 목록을 조회
	 */
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
	
	/**
	 * 후기 작성
	 */
	@Override
	public int insertNewReview(ReviewVO reviewVO) {
		return getSqlSession().insert(ReviewDao.NAME_SPACE + ".insertNewReview", reviewVO);
	}
	
	/*
	 * 후기 수정(관리자 삭제)		
	 */
	@Override
	public int updateOneReview(ReviewVO reviewVO) {
		return getSqlSession().update(ReviewDao.NAME_SPACE + ".updateOneReview", reviewVO);
	}

	/*
	 * @Override public int deleteOneReview(String rvId) { return
	 * getSqlSession().update(ReviewDao.NAME_SPACE + ".deleteOneReview", rvId); }
	 */

	/*
	 * 후기 삭제(관리자)
	 */
	@Override
	public int deleteReviewViewResult(String id) {
		return getSqlSession().update(ReviewDao.NAME_SPACE + ".deleteReviewViewResult", id);

	}

	@Override
	public int searchReviewAllCount(SearchReviewVO searchReviewVO) {
		return getSqlSession().selectOne(ReviewDao.NAME_SPACE + ".searchReviewAllCount", searchReviewVO);

	}
	
	@Override
	public int searchviewReviewCntntAllCount(SearchReviewVO searchReviewVO) {
		return getSqlSession().selectOne(ReviewDao.NAME_SPACE + ".searchviewReviewCntntAllCount", searchReviewVO);

	}

	@Override
	public List<ReviewVO> searchViewReviewCntnt(SearchReviewVO searchReviewVO) {
		return getSqlSession().selectList(ReviewDao.NAME_SPACE + ".searchViewReviewCntnt", searchReviewVO);
	}

	@Override
	public List<ReviewVO> selectManyReview(List<String> Items) {
		return getSqlSession().selectList(ReviewDao.NAME_SPACE + ".selectManyReview", Items);

	}
	
	@Override
	public int deleteManyReview(List<String> Items) {
		return getSqlSession().update(ReviewDao.NAME_SPACE + ".deleteManyReview", Items);

	}

	@Override
	public int searchAdminReviewAllCount(SearchReviewVO searchReviewVO) {
		return getSqlSession().selectOne(ReviewDao.NAME_SPACE + ".searchAdminReviewAllCount", searchReviewVO);
	}

	@Override
	public List<ReviewVO> searchAdminReview(SearchReviewVO searchReviewVO) {
		return getSqlSession().selectList(ReviewDao.NAME_SPACE + ".searchAdminReview", searchReviewVO);

	}

	/**
	 * 후기 삭제시 실행될 메서드 (삭제일, 삭제한 관리자 ID 업데이트)
	 */
	@Override
	public int reviewResultModify(Map<String, Object> modifyParam) {
	
		return getSqlSession().update(ReviewDao.NAME_SPACE + ".reviewResultModify", modifyParam);
	}
	@Override
	public int updatePtRvyn(Map<String, Object> modifyParamRvyn) {
		return getSqlSession().update(ReviewDao.NAME_SPACE + ".updatePtRvyn", modifyParamRvyn);
	}

	/**
	 * 후기 작성 여부 확인 메서드
	 */
	@Override
	public String getReviewYnByEmpIdAndPrjId(Map<String, String> param) {
		
		return getSqlSession().selectOne(ReviewDao.NAME_SPACE + ".getReviewYnByEmpIdAndPrjId", param);
	}

	@Override
	public List<ReviewVO> getReviewResult(String prjId) {
		
		return getSqlSession().selectList(ReviewDao.NAME_SPACE + ".getReviewResult", prjId);
	}

	@Override
	public int getReviewResultCount(String prjId) {
		
		return getSqlSession().selectOne(ReviewDao.NAME_SPACE + ".getReviewResultCount", prjId);
	}

	@Override
	public int getDoneEmpIdList(String prjId) {
		return getSqlSession().selectOne(ReviewDao.NAME_SPACE+".getDoneEmpIdList", prjId);
	}

	

	
	

	

	


	

}
