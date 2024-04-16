package com.ktdsuniversity.edu.pms.memo.vo;

import java.util.List;

public class MemoListVO {

	private int memoCnt;
	private List<MemoVO> memoList;

	public int getMemoCnt() {
		return memoCnt;
	}

	public void setMemoCnt(int memoCnt) {
		this.memoCnt = memoCnt;
	}

	public List<MemoVO> getMemoList() {
		return memoList;
	}

	public void setMemoList(List<MemoVO> memoList) {
		this.memoList = memoList;
	}

}
