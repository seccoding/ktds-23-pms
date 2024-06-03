package com.ktdsuniversity.edu.pms.memo.service;

import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.memo.dao.ReceiveMemoDao;
import com.ktdsuniversity.edu.pms.memo.vo.ReceiveMemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.ReceiveMemoVO;
import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiveMemoServiceImpl implements ReceiveMemoService{

    @Autowired
    private ReceiveMemoDao receiveMemoDao;

    @Override
    public ReceiveMemoListVO searchAllReceiveMemo(SearchMemoVO searchMemoVO) {

        int receiveMemoCount = this.receiveMemoDao.searchMemoAllCount(searchMemoVO);
        List<ReceiveMemoVO> receiveMemoList = this.receiveMemoDao.searchAllReceiveMemo(searchMemoVO);

        ReceiveMemoListVO receiveMemoListVO = new ReceiveMemoListVO();
        receiveMemoListVO.setRcvMemoCnt(receiveMemoCount);
        receiveMemoListVO.setReceiveMemoList(receiveMemoList);

        return receiveMemoListVO;
    }

    @Override
    public ReceiveMemoVO getOneReceiveMemo(String rcvMemoId) {

        ReceiveMemoVO receiveMemoVO = this.receiveMemoDao.selectOneReceiveMemo(rcvMemoId);
        if(receiveMemoVO == null) {
            throw new PageNotFoundException();
        }
        return receiveMemoVO;
    }

    @Override
    public List<String> searchReceiveMemoListBySendMemoId(String sendMemoId) {

        List<String> receiveMemoList = this.receiveMemoDao.searchReceiveMemoListBySendMemoId(sendMemoId);
        if(receiveMemoList == null ) {
            throw new PageNotFoundException();
        }
        return receiveMemoList;
    }

    @Override
    public boolean updateReceiveMemoDate(String rcvMemoId) {
        int updateCount = this.receiveMemoDao.updateReceiveMemoDate(rcvMemoId);
        return updateCount > 0;
    }

    @Override
    public boolean saveOneReceiveMemo(String rcvMemoId) {
        int saveCount = this.receiveMemoDao.updateSaveOneReceiveMemo(rcvMemoId);
        return saveCount > 0;
    }

    @Override
    public boolean deleteOneReceiveMemo(String rcvMemoId) {
        int deleteCount = this.receiveMemoDao.deleteOneReceiveMemo(rcvMemoId);
        return deleteCount > 0;
    }
}
