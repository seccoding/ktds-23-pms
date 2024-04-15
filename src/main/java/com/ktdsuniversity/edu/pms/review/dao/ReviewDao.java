package com.ktdsuniversity.edu.pms.review.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;

public interface ReviewDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.review.dao.ReviewDao";

	public List<ReviewVO> selectAllReview();

	/**
	 * 후기 작성 요청 전송(PM)
	 * @param reviewVO
	 * @return
	 */
	public int insertNewReviewQuestion(ReviewVO reviewVO);
	
	public int selectOneReview(ReviewVO reviewVO);
	
	/**
	 * PM, 팀원 후기 작성
	 * @param reviewVO
	 * @return
	 */
	public int insertNewReview(ReviewVO reviewVO);
	
	/**
	 * PM, 팀원 후기 수정
	 * @param reviewVO
	 * @return
	 */
	public int updateOneReview(ReviewVO reviewVO);
	
	/**
	 * PM이상이 후기를 삭제
	 * @param reviewVO
	 * @return
	 */
	public int deleteOneReview(ReviewVO reviewVO);
	
}
