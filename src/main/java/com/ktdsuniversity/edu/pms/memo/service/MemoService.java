package com.ktdsuniversity.edu.pms.memo.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.memo.vo.MemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.MemoVO;
import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;

public interface MemoService {

	public MemoListVO getSentMemoAllsearch(SearchMemoVO searchMemoVO);

	public boolean writeNewMemo(MemoVO memoVO);

	public MemoVO getOneMemo(String memoId, String empId);

	public MemoListVO getStorageMemoAllsearch(SearchMemoVO searchMemoVO);

	public MemoListVO getReceiveMemoAllsearch(SearchMemoVO searchMemoVO);

	public boolean deleteOneMemo(String id);

	public boolean deleteManyMemo(List<String> memoIds);

	public boolean saveOneMemo(MemoVO memoVO);

	public MemoListVO getReceiveMemoReadYsearch(SearchMemoVO searchMemoVO);

	public MemoVO findMemoById(String memoId);

}
