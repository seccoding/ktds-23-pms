package com.ktdsuniversity.edu.pms.memo.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.memo.vo.MemoVO;

public interface MemoDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.memo.dao.MemoDao";

	public int getSentMemoAllCount();

	public List<MemoVO> getAllSentMemo();
	
	public int getStorageMemoAllCount();

	public List<MemoVO> getAllStorageMemo();

	public int getReceiveMemoAllCount();

	public List<MemoVO> getAllReceiveMemo();
	
	public int writeNewMemo(MemoVO memoVO);

	public MemoVO selectOneMemo(String memoId);

	public int changeViewStatus(String memoId);

	public int deleteOneMemo(String memoId);

	public List<MemoVO> selectManyMemo(List<String> memoIds);

	public int deleteManyMemo(List<String> memoIds);

	public int saveOneMemo(String memoId);

	
}
