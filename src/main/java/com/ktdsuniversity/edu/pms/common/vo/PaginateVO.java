package com.ktdsuniversity.edu.pms.common.vo;

public class PaginateVO {

	/**
	 * 검색할 페이지(블록)번호
	 */
	private int pageNo;

	/**
	 * 한 목록(블록)에 노출시킬 게시글의 개수
	 */
	private int listSize;

	/**
	 * 생성할 최대 페이지의 수
	 */
	private int pageCount;

	/**
	 * 한 페이지 그룹에 보여줄 페이지 번호의 개수
	 */
	private int pageCountInGroup;

	/**
	 * 총 페이지 그룹의 개수
	 */
	private int groupCount;

	/**
	 * 현재 페이지 그룹 번호
	 */
	private int groupNo;

	/**
	 * 현재 페이지 그룹 번호의 시작 페이지 번호
	 */
	private int groupStartPageNo;

	/**
	 * 현재 페이지 그룹 번호의 끝 페이지 번호
	 */
	private int groupEndPageNo;

	/**
	 * 다음 그룹이 존재하는지 확인
	 */
	private boolean hasNextGroup;

	/**
	 * 이전 그룹이 존재하는지 확인
	 */
	private boolean hasPrevGroup;

	/**
	 * 다음 그룹의 시작 페이지 번호
	 */
	private int nextGroupStartPageNo;

	/**
	 * 이전 그룹의 시작 페이지 번호
	 */
	private int prevGroupStartPageNo;

	public PaginateVO() {
		this.listSize = 10;
		this.pageCountInGroup = 10;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int listCount) {
		// 전체 게시글을 listSize로 나누었을 때 몇 개의 페이지가 생길지 계산
		this.pageCount = (int) Math.ceil((double) listCount / this.listSize);

		// 전체 페이지 개수를 pageCountInGroup으로 나누었을 때 몇 개의 페이지 그룹이 생길지 계산
		this.groupCount = (int) Math
				.ceil((double) this.pageCount / this.pageCountInGroup);

		// 현재 보고있는 페이지 번호가 몇 번 페이지 그룹에 있는지 계산.
		this.groupNo = this.pageNo / this.pageCountInGroup;

		// 현재 페이지 그룹의 시작 페이지 번호를 계산
		this.groupStartPageNo = this.groupNo * this.pageCountInGroup;

		// 현재 페이지 그룹의 끝 페이지 번호를 계산
		this.groupEndPageNo = (this.groupNo + 1) * this.pageCountInGroup - 1;

		// 그룹의 끝 페이지 번호가 전체 페이지 번호보다 클 때, 그룹의 끝 페이지 번호를 전체 페이지 번호로 조정.
		if (this.groupEndPageNo >= this.pageCount) {
			this.groupEndPageNo = this.pageCount - 1;
		}

		if (this.groupEndPageNo < 0) {
			this.groupEndPageNo = 0;
		}

		// 현재 그룹에서 다음 그룹이 존재하는지 계산
		this.hasNextGroup = this.groupNo + 1 < this.groupCount;

		// 현재 그룹에서 이전 그룹이 존재하는지 계산
		this.hasPrevGroup = this.groupNo > 0;

		// 다음 그룹의 시작 페이지 번호를 계산
		this.nextGroupStartPageNo = this.groupEndPageNo + 1;

		// 이전 그룹의 시작 페이지 번호를 계산
		this.prevGroupStartPageNo = this.groupStartPageNo
				- this.pageCountInGroup;
	}

	public int getPageCountInGroup() {
		return pageCountInGroup;
	}

	public void setPageCountInGroup(int pageCountInGroup) {
		this.pageCountInGroup = pageCountInGroup;
	}

	public int getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getGroupStartPageNo() {
		return groupStartPageNo;
	}

	public void setGroupStartPageNo(int groupStartPageNo) {
		this.groupStartPageNo = groupStartPageNo;
	}

	public int getGroupEndPageNo() {
		return groupEndPageNo;
	}

	public void setGroupEndPageNo(int groupEndPageNo) {
		this.groupEndPageNo = groupEndPageNo;
	}

	public boolean isHasNextGroup() {
		return hasNextGroup;
	}

	public void setHasNextGroup(boolean hasNextGroup) {
		this.hasNextGroup = hasNextGroup;
	}

	public boolean isHasPrevGroup() {
		return hasPrevGroup;
	}

	public void setHasPrevGroup(boolean hasPrevGroup) {
		this.hasPrevGroup = hasPrevGroup;
	}

	public int getNextGroupStartPageNo() {
		return nextGroupStartPageNo;
	}

	public void setNextGroupStartPageNo(int nextGroupStartPageNo) {
		this.nextGroupStartPageNo = nextGroupStartPageNo;
	}

	public int getPrevGroupStartPageNo() {
		return prevGroupStartPageNo;
	}

	public void setPrevGroupStartPageNo(int prevGroupStartPageNo) {
		this.prevGroupStartPageNo = prevGroupStartPageNo;
	}

}