package com.ktdsuniversity.edu.pms.memo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.memo.dao.MemoDao;
import com.ktdsuniversity.edu.pms.memo.vo.MemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.MemoVO;

@Service
public class MemoServiceImpl implements MemoService{
	
	@Autowired
	private MemoDao memoDao;

	@Override
	public MemoListVO getSentMemoAllsearch() {
		
		int sentMemoCount = this.memoDao.getSentMemoAllCount();
		
		List<MemoVO> memoList = this.memoDao.getAllSentMemo();
		
		MemoListVO memoListVO = new MemoListVO();
		memoListVO.setMemoCnt(sentMemoCount);
		memoListVO.setMemoList(memoList);
		
		return memoListVO;
	}

	@Override
	public MemoListVO getStorageMemoAllsearch() {
		int sentMemoCount = this.memoDao.getStorageMemoAllCount();
		
		List<MemoVO> memoList = this.memoDao.getAllStorageMemo();
		
		MemoListVO memoListVO = new MemoListVO();
		memoListVO.setMemoCnt(sentMemoCount);
		memoListVO.setMemoList(memoList);
		
		return memoListVO;
	}

	@Override
	public MemoListVO getReceiveMemoAllsearch() {
		int sentMemoCount = this.memoDao.getReceiveMemoAllCount();
		
		List<MemoVO> memoList = this.memoDao.getAllReceiveMemo();
		
		MemoListVO memoListVO = new MemoListVO();
		memoListVO.setMemoCnt(sentMemoCount);
		memoListVO.setMemoList(memoList);
		
		return memoListVO;
	}
	
	
	@Transactional
	@Override
	public boolean writeNewMemo(String rcvId, String memoCntnt) {
	
		List<String> rcvIdList = Arrays.asList(rcvId.split(","));
		MemoVO memoVO = new MemoVO();
		memoVO.setMemoCntnt(memoCntnt);
		
		int insertedCount = 0;
        for (String id : rcvIdList) {
            // 각 이메일 주소에 쪽지를 보냅니다.
        	memoVO.setRcvId(id);
        	insertedCount += this.memoDao.writeNewMemo(memoVO);
        }
        // 모든 쪽지를 성공적으로 보냈을 경우에만 커밋합니다.
        return rcvIdList.size() == insertedCount;
		
	}

	// 조회한 결과가 없다면? 설정해주기
	@Transactional
	@Override
	public MemoVO getOneMemo(String memoId) {
		MemoVO memoVO = this.memoDao.selectOneMemo(memoId);
		
		// 조회하면 읽음으로 바뀜
		if(memoVO.getReadYn().equals("N")) {
			this.memoDao.changeViewStatus(memoId);
		}
		
		return memoVO;
	}

	@Transactional
	@Override
	public boolean deleteOneMemo(String id) {
		
		int deletedCount = this.memoDao.deleteOneMemo(id);
		
		return deletedCount > 0;
	}

	@Override
	public boolean deleteManyMemo(List<String> memoIds) {
		List<MemoVO> originalBoardList = this.memoDao.selectManyMemo(memoIds);
		
		int deletedCount = this.memoDao.deleteManyMemo(memoIds);
		
		return deletedCount > 0;
	}
	
	
}
