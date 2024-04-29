package com.ktdsuniversity.edu.pms.review.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.project.dao.ProjectDao;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.review.dao.ReviewDao;
import com.ktdsuniversity.edu.pms.review.vo.ReviewListVO;
import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;
import com.ktdsuniversity.edu.pms.review.vo.SearchReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService {

	private Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

	/*
	*Bean Container에 등록된 reviewDao Bean을 가져와 주입시킨다
	*/
	@Autowired
	private ReviewDao reviewDao;

	@Autowired
	private ProjectDao projectDao;

	@Override
	public ReviewListVO getAllReview(SearchReviewVO searchReviewVO) {
		
		
		ReviewListVO reviewListVO = new ReviewListVO();
		if (searchReviewVO.getEmployeeVO() != null 
				&& (searchReviewVO.getEmployeeVO().getAdmnCode().equals("301") 
						|| searchReviewVO.getEmployeeVO().getMngrYn().equals("Y"))) {
			int reviewCount = this.reviewDao.searchAdminReviewAllCount(searchReviewVO);
			searchReviewVO.setPageCount(reviewCount);
			
			List<ReviewVO> reviewList = this.reviewDao.searchAdminReview(searchReviewVO);
			
			reviewListVO.setReviewList(reviewList);
			reviewListVO.setReviewCnt(reviewCount);
		} else {
			int reviewCount = this.reviewDao.searchReviewAllCount(searchReviewVO);
			searchReviewVO.setPageCount(reviewCount);
			
			List<ReviewVO> reviewList = this.reviewDao.searchReview(searchReviewVO);
			
			reviewListVO.setReviewList(reviewList);
			reviewListVO.setReviewCnt(reviewCount);
		}
        return reviewListVO;		
	}
	
	@Override
	public ReviewListVO getAllReviewResult(SearchReviewVO searchReviewVO) {
		
		int reviewCount = this.reviewDao.searchviewReviewCntntAllCount(searchReviewVO);
		searchReviewVO.setPageCount(reviewCount);
		
		List<ReviewVO> reviewList = this.reviewDao.searchViewReviewCntnt(searchReviewVO);
		
		ReviewListVO reviewListVO = new ReviewListVO();
		reviewListVO.setReviewList(reviewList);
		reviewListVO.setReviewCnt(reviewCount);
		
        return reviewListVO;
	}
	
	
	
//	@Override
//	public ReviewListVO viewReviewCntnt() {
//		List<ReviewVO> reviewList = reviewDao.viewReviewCntnt();
//
//        ReviewListVO reviewListVO = new ReviewListVO();
//        reviewListVO.setReviewList(reviewList);
//
//        return reviewListVO;
//	}


	
//	@Override
//	public ReviewListVO viewReviewCntnt() {
//		List<ReviewVO> reviewList = reviewDao.selectAllReview();
//
//		ReviewListVO reviewListVO = new ReviewListVO();
//        reviewListVO.setReviewList(reviewList);
//
//        return reviewListVO;
//	}
	
	
	@Transactional
	@Override
	public boolean insertNewReviewQuestion(ReviewVO reviewVO) {
		return this.reviewDao.insertNewReviewQuestion(reviewVO) > 0;
	}

	
	/*
	 * @Transactional
	 * 
	 * @Override public boolean insertNewReview(ReviewVO reviewVO) { return
	 * this.reviewDao.insertNewReview(reviewVO) > 0; }
	 * 
	 * @Transactional
	 * 
	 * @Override public boolean updateReviewStatus(ProjectTeammateVO
	 * projectTeammateVO) { return
	 * projectDao.updateOneTeammateReviewStatusByProjectIdAndEmployeeId(
	 * projectTeammateVO) > 0;
	 * 
	 * }
	 */

    @Transactional
    public boolean insertNewReviewAndUpdateStatus(ReviewVO reviewVO, ProjectTeammateVO projectTeammateVO) {
        boolean reviewResult = reviewDao.insertNewReview(reviewVO) > 0;
        boolean statusResult = projectDao.updateOneTeammateReviewStatusByProjectIdAndEmployeeId(projectTeammateVO) > 0;
        
        // 두 작업 모두 성공했을 경우에만 true 반환
        return reviewResult && statusResult;
    }
	
	
	@Transactional
	@Override
	public boolean updateOneReview(ReviewVO reviewVO) {
		return this.reviewDao.insertNewReviewQuestion(reviewVO) > 0;
	}

	@Override
	public ReviewVO getOneReview(String rvId, boolean isdeleted) {
		ReviewVO reviewVO = this.reviewDao.getOneReview(rvId);
		return reviewVO;
	}

	@Transactional
	@Override
	public boolean reviewViewResultDelete(String id) {
		int cnt = this.reviewDao.deleteReviewViewResult(id);
		logger.debug("cnt : {}", cnt);
		return true;
	}

	@Transactional
	@Override
	public boolean deleteManyReview(List<String> reviewIds) {		
		int deletedCount = this.reviewDao.deleteManyReview(reviewIds);
		
		return deletedCount > 0;
	}

	
	
}
