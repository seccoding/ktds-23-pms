package com.ktdsuniversity.edu.pms.memo.dao;

import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;
import com.ktdsuniversity.edu.pms.memo.vo.SendMemoVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SendMemoDaoImpl extends SqlSessionDaoSupport implements SendMemoDao {

    @Override
    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int searchMemoAllCount(SearchMemoVO searchMemoVO) {
        return getSqlSession().selectOne(SendMemoDao.NAME_SPACE + ".searchAllSendMemoCount", searchMemoVO);
    }

    @Override
    public List<SendMemoVO> searchAllSendMeno(SearchMemoVO searchMemoVO) {
        return getSqlSession().selectList(SendMemoDao.NAME_SPACE + ".searchAllSendMemo", searchMemoVO);
    }

    @Override
    public SendMemoVO selectOneSendMemo(String sendMemoId) {
        return getSqlSession().selectOne(SendMemoDao.NAME_SPACE + ".selectOneSendMemo", sendMemoId);
    }

    @Override
    public String selectSendMemoId() {
        return getSqlSession().selectOne(SendMemoDao.NAME_SPACE + ".selectSendMemoId");
    }

    @Override
    public int insertNewSendMemo(SendMemoVO sendMemoVO) {
        return getSqlSession().insert(SendMemoDao.NAME_SPACE + ".insertNewSendMemo", sendMemoVO);
    }

    @Override
    public int updateSaveOneSendMemo(SendMemoVO sendMemoVO) {
        return getSqlSession().update(SendMemoDao.NAME_SPACE + ".updateSaveOneSendMemo", sendMemoVO);
    }

    @Override
    public int updateSendStatus(String sendMemoId) {
        return getSqlSession().update(SendMemoDao.NAME_SPACE + ".updateSendStatus", sendMemoId);
    }

    @Override
    public int deleteOneSendMemo(String sendMemoId) {
        return getSqlSession().update(SendMemoDao.NAME_SPACE + ".deleteOneSendMemo", sendMemoId);
    }
    
    @Override
    public int getSendCountBySendMemoId(String sendMemoId) {
        return getSqlSession().selectOne(SendMemoDao.NAME_SPACE + ".getSendCountBySendMemoId", sendMemoId);
    }
}
