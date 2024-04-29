package com.ktdsuniversity.edu.pms.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.review.vo.ReviewListVO;
import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;
import com.ktdsuniversity.edu.pms.review.vo.SearchReviewVO;

public interface ReviewService {

	ReviewListVO getAllReview(SearchReviewVO searchReviewVO);

	ReviewListVO getAllReviewResult(SearchReviewVO searchReviewVO);

	/*
	 * public boolean updateReviewStatus(ProjectTeammateVO projectTeammateVO);
	 * 
	 *//**
		 * 후기를 등록한다
		 * 
		 * @param reviewVO 등록한 후기글의 내용
		 * @return 후기글 등록 성공 여부
		 *//*
			 * public boolean insertNewReview(ReviewVO reviewVO);
			 */

	/*
	 * 후기등록, status 업데이트 로직 합침
	 */
	public boolean insertNewReviewAndUpdateStatus(ReviewVO reviewVO, ProjectTeammateVO projectTeammateVO);
	
	
	/**
	 * 후기 작성 요청 전송(PM)
	 * 
	 * @param reviewVO
	 * @return
	 */
	public boolean insertNewReviewQuestion(ReviewVO reviewVO);

	/**
	 * 후기 하나 조회
	 * 
	 * @param reviewVO
	 * @return
	 */
	public ReviewVO getOneReview(String rvId, boolean isdeleted);

	/**
	 * 전달받은 후기정보로 후기글를 수정한다
	 * 
	 * @param reviewVO 수정할 후기글의 정보
	 * @return 후기 수정 성공 여부
	 */
	public boolean updateOneReview(ReviewVO reviewVO);

	/**
	 * 후기 삭제
	 * 
	 * @param id 삭제하는 후기 id
	 * @return 후기삭제성공여부
	 */
	public boolean reviewViewResultDelete(String id);

	boolean deleteManyReview(List<String> reviewIds);

}
