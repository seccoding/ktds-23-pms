package com.ktdsuniversity.edu.pms.ckeditor.service;

import com.ktdsuniversity.edu.pms.ckeditor.dao.CkeditorDao;
import com.ktdsuniversity.edu.pms.ckeditor.vo.CkeditorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CkeditorService {

    @Autowired
    private CkeditorDao ckeditorDao;

    public boolean saveContent(CkeditorVO ckeditorVO) {
        return ckeditorDao.save(ckeditorVO) > 0;
    }

    public List<CkeditorVO> allContent() {
        return ckeditorDao.findAll();
    }

    public CkeditorVO selectOneContent(String id) {
        return ckeditorDao.findById(id);
    }
}
