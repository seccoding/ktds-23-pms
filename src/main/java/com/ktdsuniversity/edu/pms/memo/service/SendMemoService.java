package com.ktdsuniversity.edu.pms.memo.service;

import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;
import com.ktdsuniversity.edu.pms.memo.vo.SendMemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.SendMemoVO;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SendMemoService {
    /**
     * 전체 발신 쪽지 조회
     */
    public SendMemoListVO searchAllSendMemo(SearchMemoVO searchMemoVO);

    /**
     * 전체 보관 발신 쪽지 조회 
     */
//	public SendMemoListVO searchAllSaveSendMemo(SearchMemoVO searchMemoVO);

    /**
     * 한 개의 발신 쪽지 조회
     */
    public SendMemoVO getOneSendMemo(String sendMemoId);

    /**
     * 쪽지 발신
     */
    public boolean createSendMemo(SendMemoVO sendMemoVO, MultipartFile file);

    /**
     * 쪽지 발신 취소
     */
    public boolean cancelOneSendMemo(String sendMemoId, int rcvMemoCount);

    /**
     * 발신 쪽지 저장
     */
    public boolean saveOneSendMemo(SendMemoVO sendMemoVO);

    /**
     * 발신 쪽지 삭제
     */
    public boolean deleteOneSendMemo(String sendMemoId);
    
    /**
     * 발신자로 수신 쪽지 개수 조회
     */
    public int getSendCountBySendMemoId(String sendMemoId);

    /**
     * 파일 다운로드
     */
	public ResponseEntity<Resource> getDownloadFile(SendMemoVO sendMemoVO);

}
