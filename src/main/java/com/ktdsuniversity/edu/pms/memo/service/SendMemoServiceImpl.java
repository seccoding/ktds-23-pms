package com.ktdsuniversity.edu.pms.memo.service;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.memo.dao.ReceiveMemoDao;
import com.ktdsuniversity.edu.pms.memo.dao.SendMemoDao;
import com.ktdsuniversity.edu.pms.memo.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class SendMemoServiceImpl implements SendMemoService{

    @Autowired
    private SendMemoDao sendMemoDao;
    @Autowired
    private ReceiveMemoDao receiveMemoDao;
    @Autowired
    private FileHandler fileHandler;

    @Override
    public SendMemoListVO searchAllSendMemo(SearchMemoVO searchMemoVO) {

        int sendMemoCount = this.sendMemoDao.searchMemoAllCount(searchMemoVO);
        List<SendMemoVO> sendMemoList = this.sendMemoDao.searchAllSendMeno(searchMemoVO);

        SendMemoListVO sendMemoListVO = new SendMemoListVO();
        sendMemoListVO.setSendMenoCnt(sendMemoCount);
        sendMemoListVO.setSendMemoList(sendMemoList);

        return sendMemoListVO;
    }

    @Override
    public SendMemoVO getOneSendMomo(String sendMemoId) {

        SendMemoVO sendMemoVO = this.sendMemoDao.selectOneSendMemo(sendMemoId);
        if(sendMemoVO == null) {
            throw new PageNotFoundException();
        }
        return sendMemoVO;
    }

    @Transactional
    @Override
    public boolean createSendMemo(SendMemoVO sendMemoVO, MultipartFile file) {

        // 1.file handling
        if(file != null && !file.isEmpty()) {
            FileHandler.StoredFile storedFile = fileHandler.storeFile(file);
            if (storedFile != null) {
                sendMemoVO.setFileName(storedFile.getRealFileName());
                sendMemoVO.setOriginFileName(storedFile.getFileName());
            }
        }
        // 2.send memo(1)
        String newSendMemoId = this.sendMemoDao.selectSendMemoId();
        sendMemoVO.setSendMemoId(newSendMemoId);
        int insertSendCount = this.sendMemoDao.insertNewSendMemo(sendMemoVO);

        // 3.receive memo(n)
        List<ReceiveMemoVO> newReceiveMemoVOList = new ArrayList<>();
        for(ReceiveMemoVO receiveMemoVO : sendMemoVO.getReceiveMemoVOList()) {
            receiveMemoVO.setSendMemoId(newSendMemoId);
            newReceiveMemoVOList.add(receiveMemoVO);
        }
        int insertReceiveCount = this.receiveMemoDao.insertNewReceiveMemo(newReceiveMemoVOList);

        boolean isInsertSuccess = (insertSendCount > 0) &&
                (sendMemoVO.getReceiveMemoVOList().size() == insertReceiveCount);
        return isInsertSuccess;
    }

    @Transactional
    @Override
    public boolean cancelOneSendMemo(String sendMemoId, List<String> receiveMemoIdList) {
        // 1.수신 쪽지 조회
        // List<String> receiveMemoList = this.receiveMemoDao.searchReceiveMemoListBySendMemoId(sendMemoId);
        // 2.수신 쪽지 삭제
        int deleteReceiveMemoCount = this.receiveMemoDao.deleteManyReceiveMemo(receiveMemoIdList);
        if(receiveMemoIdList.size() != deleteReceiveMemoCount) {
            throw new PageNotFoundException();
        }
        // 3.발신 취소 처리
        int cancelCount = this.sendMemoDao.updateSendStatus(sendMemoId);
        return cancelCount > 0;
    }

    @Transactional
    @Override
    public boolean saveOneSendMemo(String sendMemoId) {
        int saveSendMemoCount = this.sendMemoDao.updateSaveOneSendMemo(sendMemoId);
        return saveSendMemoCount > 0;
    }

    @Transactional
    @Override
    public boolean deleteOneSendMemo(String sendMemoId) {

        // 1.delete file
        SendMemoVO sendMemoVO = this.sendMemoDao.selectOneSendMemo(sendMemoId);
        if(sendMemoVO != null) {
            String storedFileName = sendMemoVO.getFileName();
            if(storedFileName != null && storedFileName.length() > 0) {
                this.fileHandler.deleteFileByFileName(storedFileName);
            }
        }
        // 2.delete memo
        int deleteSendMemoCount = this.sendMemoDao.deleteOneSendMemo(sendMemoId);
        return deleteSendMemoCount > 0;
    }
}