package com.ktdsuniversity.edu.pms.memo.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.memo.vo.MemoVO;

public interface MemoDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.memo.dao.MemoDao";

	public int getSentMemoAllCount();

	public List<MemoVO> getSentAllMemo();
	
}
