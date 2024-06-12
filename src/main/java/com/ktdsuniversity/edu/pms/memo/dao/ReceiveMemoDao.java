package com.ktdsuniversity.edu.pms.memo.dao;

import com.ktdsuniversity.edu.pms.memo.vo.ReceiveMemoVO;
import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;

import java.util.List;

public interface ReceiveMemoDao {

    public String NAME_SPACE = "com.ktdsuniversity.edu.pms.memo.dao.ReceiveMemoDao";

    /**
     * 수신 메모 개수 조회
     */
    public int searchMemoAllCount(SearchMemoVO searchMemoVO);

    /**
     * 수신 메모 목록(정보) 조회
     */
    public List<ReceiveMemoVO> searchAllReceiveMemo(SearchMemoVO searchMemoVO);

    /**
     * 쪽지 수신
     */
    public int insertNewReceiveMemo(List<ReceiveMemoVO> newReceiveMemoVOList);

    /**
     * 한 개의 수신 쪽지 조회
     */
    public ReceiveMemoVO selectOneReceiveMemo(String rcvMemoId);

    /**
     * 발신 쪽지 ID로 수신 쪽지 ID 조회
     */
    List<String> searchReceiveMemoListBySendMemoId(String sendMemoId);

    /**
     * 수신 쪽지 읽음 처리
     */
    public int updateReceiveMemoDate(String rcvMemoId);

    /**
     * 수신 쪽지 보관
     */
    public int updateSaveOneReceiveMemo(ReceiveMemoVO receiveMemoVO);

    /**
     * 수신 쪽지 삭제
     */
    public int deleteOneReceiveMemo(String rcvMemoId);

    /**
     * 수신 쪽지 여러개 삭제
     */
    public int deleteManyReceiveMemo(List<String> receiveMemoList);
    
    public int getRcvCountBySendMemoId(String sendMemoId);

    public int deleteReceiveMemoBySendMemoId(String sendMemoId);
}
