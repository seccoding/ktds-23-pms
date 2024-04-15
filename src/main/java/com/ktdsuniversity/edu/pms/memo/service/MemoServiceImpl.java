package com.ktdsuniversity.edu.pms.memo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
}
