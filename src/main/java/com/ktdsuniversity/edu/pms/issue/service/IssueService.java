package com.ktdsuniversity.edu.pms.issue.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.issue.vo.IssueListVO;
import com.ktdsuniversity.edu.pms.issue.vo.IssueVO;
import com.ktdsuniversity.edu.pms.issue.vo.SearchIssueVO;

public interface IssueService {

	/**
	 * 이슈 목록과 이슈의 개수를 모두 조회한다
	 * @return
	 */
	public IssueListVO getAllIssue();
	
	public IssueListVO searchAllIssue(SearchIssueVO searchIssueVO);
	
	/**
	 * 전달받은 파라미터의 이슈 번호를 조회해 반환한다
	 * 이슈 조회시, 이슈 조회수도 1 증가해야한다
	 * @param isId 사용자가 조회하려는 이슈 번호
	 * @param isIncrease 이 값이 true일 때, 조회수가 증가한다
	 * @return 이슈 정보
	 */
	public IssueVO getOneIssue(String isId, boolean isIncrease);
	
	/**
	 * 새로운 이슈를 등록한다
	 * @param issueVO 사용자가 입력한 이슈의 내용
	 * @param file 
	 * @return 이슈 등록 성공 여부
	 */
	public boolean createNewIssue(IssueVO issueVO, MultipartFile file);
	
	/**
	 * 전달받은 이슈 정보로 이슈를 수정한다
	 * @param issueVO 수정할 이슈의 정보
	 * @return 수정 성공 여부
	 */
	public boolean updateOneIssue(IssueVO issueVO, MultipartFile file);
	
	/**
	 * 전달받은 이슈의 번호로 이슈를 삭제한다
	 * 단, 물리적인 삭제가 아니라 논리적 삭제 수행
	 * @param isId 삭제할 이슈의 번호
	 * @return 삭제 성공 여부
	 */
	public boolean deleteOneIssue(String isId);

	public boolean deleteManyIssue(List<String> deleteItems);

	public List<IssueVO> searchIssueByPrjId(String prjId);
}
