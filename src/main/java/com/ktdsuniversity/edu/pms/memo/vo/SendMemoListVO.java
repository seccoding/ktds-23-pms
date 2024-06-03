package com.ktdsuniversity.edu.pms.memo.vo;

import java.util.List;

public class SendMemoListVO {

    private int sendMenoCnt;
    private List<SendMemoVO> sendMemoList;

    public int getSendMenoCnt() {
        return sendMenoCnt;
    }

    public void setSendMenoCnt(int sendMenoCnt) {
        this.sendMenoCnt = sendMenoCnt;
    }

    public List<SendMemoVO> getSendMemoList() {
        return sendMemoList;
    }

    public void setSendMemoList(List<SendMemoVO> sendMemoList) {
        this.sendMemoList = sendMemoList;
    }
}
