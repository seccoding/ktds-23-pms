package com.ktdsuniversity.edu.pms.memo.service;

import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;
import com.ktdsuniversity.edu.pms.memo.vo.SendMemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.SendMemoVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SendMemoService {
    /**
     * 전체 발신 쪽지 조회
     */
    public SendMemoListVO searchAllSendMemo(SearchMemoVO searchMemoVO);

    /**
     * 한 개의 발신 쪽지 조회
     */
    public SendMemoVO getOneSendMomo(String sendMemoId);

    /**
     * 쪽지 발신
     */
    public boolean createSendMemo(SendMemoVO sendMemoVO, MultipartFile file);

    /**
     * 쪽지 발신 취소
     */
    public boolean cancelOneSendMemo(String sendMemoId, List<String> receiveMemoIdList);

    /**
     * 발신 쪽지 저장
     */
    public boolean saveOneSendMemo(String sendMemoId);

    /**
     * 발신 쪽지 삭제
     */
    public boolean deleteOneSendMemo(String sendMemoId);
}
