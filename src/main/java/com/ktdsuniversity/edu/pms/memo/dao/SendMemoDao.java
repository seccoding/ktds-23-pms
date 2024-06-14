package com.ktdsuniversity.edu.pms.memo.dao;

import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;
import com.ktdsuniversity.edu.pms.memo.vo.SendMemoVO;

import java.util.List;

public interface SendMemoDao {

    public String NAME_SPACE = "com.ktdsuniversity.edu.pms.memo.dao.SendMemoDao";

    /**
     * 발신 쪽지 개수 조회
     */
    public int searchMemoAllCount(SearchMemoVO searchMemoVO);

    /**
     * 발신 쪽지 목록(정보) 조회
     */
    public List<SendMemoVO> searchAllSendMeno(SearchMemoVO searchMemoVO);
    
    /**
     * 한 개의 발신 쪽지 조회
     */
    public SendMemoVO selectOneSendMemo(String sendMemoId);

    /**
     * 발신 메모 ID select
     */
    public String selectSendMemoId();

    /**
     * 쪽지 발신
     */
    public int insertNewSendMemo(SendMemoVO sendMemoVO);

    /**
     * 쪽지 저장
     */
    public int updateSaveOneSendMemo(SendMemoVO sendMemoVO);

    /**
     * 쪽지 발신 취소
     */
    public int updateSendStatus(String sendMemoId);

    /**
     * 쪽지 삭제
     */
    public int deleteOneSendMemo(String sendMemoId);
    
    public int getSendCountBySendMemoId(String sendMemoId);

}
