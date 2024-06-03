package com.ktdsuniversity.edu.pms.memo.vo;

import java.util.List;

public class ReceiveMemoListVO {

    private int rcvMemoCnt;
    private List<ReceiveMemoVO> receiveMemoList;

    public int getRcvMemoCnt() {
        return rcvMemoCnt;
    }

    public void setRcvMemoCnt(int rcvMemoCnt) {
        this.rcvMemoCnt = rcvMemoCnt;
    }

    public List<ReceiveMemoVO> getReceiveMemoList() {
        return receiveMemoList;
    }

    public void setReceiveMemoList(List<ReceiveMemoVO> receiveMemoList) {
        this.receiveMemoList = receiveMemoList;
    }
}
