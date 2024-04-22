package com.ktdsuniversity.edu.pms.review.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.review.vo.ReviewListVO;
import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;
import com.ktdsuniversity.edu.pms.review.vo.SearchReviewVO;

public interface ReviewService {

	ReviewListVO getAllReview(SearchReviewVO searchReviewVO);
	ReviewListVO getAllReviewResult(SearchReviewVO searchReviewVO);

	
//	ReviewListVO viewReviewCntnt();
//	/**
//	 * 작성완료된 후기의 내용을 보여준다
//	 * @return
//	 */
//	ReviewListVO viewReviewCntnt();
//	
	/**
	 * 후기 작성 요청 전송(PM)
	 * @param reviewVO
	 * @return
	 */
	public boolean insertNewReviewQuestion(ReviewVO  reviewVO);
	
	/**
	 * 후기 하나 조회
	 * @param reviewVO
	 * @return
	 */
	public ReviewVO getOneReview(String rvId, boolean isdeleted);
	
	/**
	 * 후기를 등록한다
	 * @param reviewVO 등록한 후기글의 내용
	 * @return 후기글 등록 성공 여부
	 */
	public boolean insertNewReview(ReviewVO reviewVO);
	
	/**
	 * 전달받은 후기정보로 후기글를 수정한다
	 * @param reviewVO 수정할 후기글의 정보 
	 * @return 후기 수정 성공 여부
	 */
	public boolean updateOneReview(ReviewVO reviewVO);
	
	/**
	 * 후기 삭제(PM이상)
	 * @param id 삭제할 후기의 번호
	 * @return 후기 삭제 성공 여부
	 */
//	public boolean deleteOneReview(String rvId, String email);


	/**
	 * 후기 삭제
	 * @param id 삭제하는 후기 id
	 * @return 후기삭제성공여부
	 */
	public boolean reviewViewResultDelete(String id);
	
	boolean deleteManyReview(List<String> reviewIds);


	
}
