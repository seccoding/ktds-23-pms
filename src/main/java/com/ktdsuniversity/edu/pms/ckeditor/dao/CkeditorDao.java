package com.ktdsuniversity.edu.pms.ckeditor.dao;

import com.ktdsuniversity.edu.pms.ckeditor.vo.CkeditorVO;

import java.util.List;

public interface CkeditorDao {
    public String NAME_SPACE = "com.ktdsuniversity.edu.pms.ckeditor.dao.CkeditorDao";

    int save(CkeditorVO ckeditorVO);

    List<CkeditorVO> findAll();

    CkeditorVO findById(String id);
}
