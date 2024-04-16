package com.ktdsuniversity.edu.pms.memo.service;

import com.ktdsuniversity.edu.pms.memo.vo.MemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.MemoVO;

public interface MemoService {

	MemoListVO getSentMemoAllsearch();

	boolean writeNewMemo(MemoVO memoVO);

	MemoVO getOneMemo(String memoId);

}
