package com.ktdsuniversity.edu.pms.issue.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.issue.vo.IssueVO;
import com.ktdsuniversity.edu.pms.issue.vo.SearchIssueVO;

public interface IssueDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.issue.dao.IssueDao";
	
	/**
	 * 모든 이슈 개수 조회
	 * @return
	 */
	public int getAllIssueCount();
	
	/**
	 * 모든 이슈를 목록 조회
	 * @return
	 */
	public List<IssueVO> getAllIssue();
	
	public List<IssueVO> searchAllIssue(SearchIssueVO searchIssueVO);
	
	public int searchIssueAllCount(SearchIssueVO searchIssueVO);
	
	/**
	 * 전달받은 파라미터로 DB에서 이슈를 조회해 반환한다
	 * @param isId 조회하려는 이슈의 번호
	 * @return 조회된 이슈의 정보
	 */
	public IssueVO selectOneIssue(String isId);
	
	
	/**
	 * 전달받은 파라미터로 DB에서 해당 이슈의 조회수를 1 증가시킨다
	 * @param isId 조회수를 증가시키려는 이슈의 번호
	 * @return 업데이트 영향을 받은 데이터의 건수
	 */
	public int increaseViewCount(String isId);
	
	/**
	 * 새로운 이슈를 등록한다
	 * @param issueVO 사용자가 입력한 이슈 정보
	 * @return insert한 개수
	 */
	public int insertNewIssue(IssueVO issueVO);
	
	/**
	 * 전달받은 이슈 정보로 이슈를 수정한다
	 * 이슈가 수정될 때, 수정날짜도 변경된다
	 * @param issueVO 사용자가 입력한 수정될 이슈의 정보
	 * @return 업데이트 영향을 받은 데이터의 건수
	 */
	public int updateOneIssue(IssueVO issueVO);
	
	/**
	 * 전달받은 이슈 번호로 이슈를 물리적 삭제한다
	 * @param isId 삭제할 이슈 번호
	 * @return 삭제 영향을 받은 데이터의 건수
	 */
	public int deleteOneIssue(String isId);

	public List<IssueVO> selectManyIssue(List<String> deleteItems);

	public int deleteManyIssue(List<String> deleteItems);

	public List<IssueVO> searchIssueByPrjId(String prjId);

}
