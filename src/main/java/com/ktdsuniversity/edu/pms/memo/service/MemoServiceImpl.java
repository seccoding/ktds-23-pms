package com.ktdsuniversity.edu.pms.memo.service;

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

	@Transactional
	@Override
	public boolean writeNewMemo(MemoVO memoVO) {
		
		int insertedCount = this.memoDao.writeNewMemo(memoVO);
		
		return insertedCount > 0;
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
	
	
}
