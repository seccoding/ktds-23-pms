package com.ktdsuniversity.edu.pms.review.dao;

import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;
import com.ktdsuniversity.edu.pms.review.vo.SearchReviewVO;

public interface ReviewDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.review.dao.ReviewDao";

//	public List<ReviewVO> selectAllReview();
	
	/**
	 * 검색어에 대응하기위한 count 만들어주기
	 * @param searchBoardVO
	 * @return
	 */
	public int searchReviewAllCount(SearchReviewVO searchReviewVO);
	
	public int searchviewReviewCntntAllCount(SearchReviewVO searchReviewVO);

	/**
	* DB에 저장된 모든 후기의 목록을 조회 - pagination을 위함
	* @param searchReviewVO 검색할 조건 (페이지번호, 노출할 목록개수 등)
	* @return DB에서 조회된 후기의 목록
	*/
	public List<ReviewVO> searchReview(SearchReviewVO searchReviewVO);
		
	public List<ReviewVO> searchViewReviewCntnt(SearchReviewVO searchReviewVO);

	
	
	public List<ReviewVO> viewReviewCntnt();


	
	/**
	 * 후기 작성 요청 전송(PM)
	 * @param reviewVO
	 * @return 전송된 후기 작성 요청의 건수
	 */
	public int insertNewReviewQuestion(ReviewVO reviewVO);
	
	public ReviewVO getOneReview(String rvId);
	
	/**
	 * PM, 팀원 후기 작성
	 * @param reviewVO
	 * @return 작성된 후기의 개수
	 */
	public int insertNewReview(ReviewVO reviewVO);
	
	/**
	 * PM, 팀원 후기 수정
	 * @param reviewVO
	 * @return 수정한 개수
	 */
	public int updateOneReview(ReviewVO reviewVO);
	
	/**
	 * PM이상이 후기를 삭제
	 * @param reviewVO
	 * @return 삭제한 개수
	 */
//	public int deleteOneReview(String rvId);

	/**
	 * 후기 삭제
	 * @param id 후기id
	 * @return 삭제 count
	 */
	public int deleteReviewViewResult(String id);

	public int deleteManyReview(List<String> reviewIds);

	public List<ReviewVO> selectManyReview(List<String> reviewIds);

	public int searchAdminReviewAllCount(SearchReviewVO searchReviewVO);

	public List<ReviewVO> searchAdminReview(SearchReviewVO searchReviewVO);

	/**
	 * 리뷰 삭제시 실행되는 수정 메서드
	 * @param reviewId 수정될 후기의 PK
	 * @return 성공 여부 int 반환
	 */
	public int reviewResultModify(Map<String, Object> modifyParam);
	public int updatePtRvyn(Map<String, Object> modifyParamRvyn);

	/**
	 * 리뷰 작성 가능 여부를 반환하는 메서드
	 * @param empId 로그인 되어 있는 사원의 ID
	 * @param prjId 로그인 한 사원이 참여하는 프로젝트 
	 * @return 작성 여부에 따라 int 를 반환
	 */
	public String getReviewYnByEmpIdAndPrjId(Map<String, String> param);

	public List<ReviewVO> getReviewResult(String prjId);

	public int getReviewResultCount(String prjId);

	public int getDoneEmpIdList(String prjId);
	
	
	
}
