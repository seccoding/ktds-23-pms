package com.ktdsuniversity.edu.pms.approval.service;



import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;

public interface ApprovalService {

	/**
	 * 모든 결재목록과 건수를 조회한다.
	 * @return
	 */
	public ApprovalListVO getAllApproval();
	
	/**
	 * 사용자가 작성한 전체 결재 목록과 건수를 조회한다.
	 * @return
	 */
	public ApprovalListVO getAllApprovalByEmpId(String empId);


	public ApprovalVO selectOneApproval(String apprId);


    /**
	 * 클릭시 결재 상태로 바뀐다 
	*/
   public boolean updatesOneApproval(String id);
   
   /**
    * 승인되지 않은 결재 내역 개수
    */
   public ApprovalListVO getAllApprove();
   
   /**
    * 일주일이상 지연된 결재
    */
   public ApprovalListVO getAllOneWeekApproval();
   
   /**
    * 한 달 이내 결재내역
    */
   public ApprovalListVO getAllMonthApproval();

    public ApprovalVO selectOneApprovalAll(String apprId);

	public boolean approvalStatusChange(ApprovalVO approvalVO);

    public boolean deleteOneApproval(String apprId);

}
