package com.ktdsuniversity.edu.pms.memo.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.memo.vo.MemoVO;
import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;

public interface MemoDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.memo.dao.MemoDao";

	public int getSentMemoAllCount(SearchMemoVO searchMemoVO);

	public List<MemoVO> getAllSentMemo(SearchMemoVO searchMemoVO);
	
	public int getStorageMemoAllCount(SearchMemoVO searchMemoVO);

	public List<MemoVO> getAllStorageMemo(SearchMemoVO searchMemoVO);

	public int getReceiveMemoAllCount(SearchMemoVO searchMemoVO);

	public List<MemoVO> getAllReceiveMemo(SearchMemoVO searchMemoVO);
	
	public int writeNewMemo(MemoVO memoVO);

	public MemoVO selectOneMemo(String memoId);

	public int changeViewStatus(String memoId);

	public int deleteOneMemo(String memoId);

	public List<MemoVO> selectManyMemo(List<String> memoIds);

	public int deleteManyMemo(List<String> memoIds);

	public int saveOneMemo(MemoVO memoVO);

	public int getReceiveMemoReadYCount(SearchMemoVO searchMemoVO);

	public List<MemoVO> getReadYReceiveMemo(SearchMemoVO searchMemoVO);

	public MemoVO findMemo(String memoId);

	
}
