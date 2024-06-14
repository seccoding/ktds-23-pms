package com.ktdsuniversity.edu.pms.requirement.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.requirement.vo.RequirementListVO;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementSearchVO;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;

public interface RequirementService {
/**
 * 프로젝트 아이디를 파악하여 전체 요구사항 리스트를 가져온다
 * @return 전체 요구사항 리스트(삭제 제외)
 */
	public List<RequirementVO> getAllRequirement();
	public List<RequirementVO> getAllRequirement(String prjId);
	
	public RequirementListVO searchAllRequirement(RequirementSearchVO requirementSearchVO);
/**
 * 요구사항 아이디값을 받아서 요구사항을 보내준다
 * @param rqmId 요구사항 아이디
 * @return 요구사항
 */
	public RequirementVO getOneRequirement(String rqmId);
	/**
	 * 요구사항 하나를 insert한다
	 * @param requirementVO insert할 요구사항의 정보
	 * @param file 파일
	 * @return 성공갯수가 1이상이면 true
	 */
	public boolean insertOneRequirement(RequirementVO requirementVO, MultipartFile file);

	/**
 *  요구사항 하나를 입력한다
 * @param requirementVO 요구사항 정보
 * @param file 파일이 있을경우 파일
 * @return 성공갯수가 1이상이면 true
 */
	public boolean updateRequirement(RequirementVO requirementVO, MultipartFile file);
	
	public boolean delayRequirement(RequirementVO requirementVO);
/**
 * 연기요청을 승인 혹은 거절시 실행 
 * 승인시 연기되고 완성일 1주일 연장
 * 거절시 연기요청 취소
 * @param rqmId 요구사항 아이디
 * @param isApprove 승인여부 
 * @return 성공갯수가 1이상이면 true
 */
	public boolean updateDelayRequirement(String rqmId,  boolean isApprove);

	public boolean deleteOneRequirement(RequirementVO RequirementVO);
	public ResponseEntity<Resource> getDownloadFile(RequirementVO requirement);
	
	public List<RequirementVO> getAllRequirementByTeammateId(String empId);
	public boolean updateTestResult(RequirementVO requirementVO, boolean testApprove);
}
