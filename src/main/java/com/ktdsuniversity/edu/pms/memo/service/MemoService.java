package com.ktdsuniversity.edu.pms.memo.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.memo.vo.MemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.MemoVO;

public interface MemoService {

	public MemoListVO getSentMemoAllsearch();

	public boolean writeNewMemo(String rcvId, String memoCntnt);

	public MemoVO getOneMemo(String memoId);

	public MemoListVO getStorageMemoAllsearch();

	public MemoListVO getReceiveMemoAllsearch();

	public boolean deleteOneMemo(String id);

	public boolean deleteManyMemo(List<String> memoIds);

	public boolean saveOneMemo(String id);

}
