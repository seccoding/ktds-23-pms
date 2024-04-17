package com.ktdsuniversity.edu.pms.memo.service;

import com.ktdsuniversity.edu.pms.memo.vo.MemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.MemoVO;

public interface MemoService {

	MemoListVO getSentMemoAllsearch();

	public boolean writeNewMemo(MemoVO memoVO);

	public MemoVO getOneMemo(String memoId);

	MemoListVO getStorageMemoAllsearch();

	MemoListVO getReceiveMemoAllsearch();

}
