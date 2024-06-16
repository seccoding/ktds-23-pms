package com.ktdsuniversity.edu.pms.memo.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import com.ktdsuniversity.edu.pms.memo.vo.ReceiveMemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.ReceiveMemoVO;
import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;

public interface ReceiveMemoService {

    /**
     * 로그인한 사원의 수신 쪽지 전체 조회
     */
    public ReceiveMemoListVO searchAllReceiveMemo(SearchMemoVO searchMemoVO);

    /**
     * 한 개의 수신 쪽지 조회
     */
    public ReceiveMemoVO getOneReceiveMemo(String rcvMemoId);

    /**
     * 동일한 발신Id를 가진 수신 쪽지ID 조회
     */
    public List<String> searchReceiveMemoListBySendMemoId(String sendMemoId);

    /**
     * 수신 쪽지 읽음
     */
    public boolean updateReceiveMemoDate(String rcvMemoId);

    /**
     * 수신 쪽지 보관
     */
    public boolean saveOneReceiveMemo(ReceiveMemoVO receiveMemoVO);

    /**
     * 수신 쪽지 삭제
     */
    public boolean deleteOneReceiveMemo(String rcvMemoId);
    
    /**
     * 수신 쪽지 개수 확인
     */
    public int getRcvCountBySendMemoId(String sendMemoId);
    
    /**
     * 첨부파일 다운로드
     */
    public ResponseEntity<Resource> getDownloadFile(ReceiveMemoVO receiveMemoVO);

}
