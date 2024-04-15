package com.ktdsuniversity.edu.pms.ckeditor.dao;

import com.ktdsuniversity.edu.pms.ckeditor.vo.CkeditorVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CkeditorDaoImpl extends SqlSessionDaoSupport implements CkeditorDao {

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int save(CkeditorVO ckeditorVO) {
        return getSqlSession().insert(CkeditorDao.NAME_SPACE + ".save", ckeditorVO);
    }

    @Override
    public List<CkeditorVO> findAll() {
        return getSqlSession().selectList(CkeditorDao.NAME_SPACE + ".findAll");
    }

    @Override
    public CkeditorVO findById(String id) {
        return getSqlSession().selectOne(CkeditorDao.NAME_SPACE + ".findById", id);
    }
}
