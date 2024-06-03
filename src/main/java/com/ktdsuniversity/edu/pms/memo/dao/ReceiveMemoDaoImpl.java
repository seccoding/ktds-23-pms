package com.ktdsuniversity.edu.pms.memo.dao;

import com.ktdsuniversity.edu.pms.memo.vo.ReceiveMemoVO;
import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReceiveMemoDaoImpl extends SqlSessionDaoSupport implements ReceiveMemoDao{

    @Override
    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int searchMemoAllCount(SearchMemoVO searchMemoVO) {
        return getSqlSession().selectOne(ReceiveMemoDao.NAME_SPACE + ".searchMemoAllCount", searchMemoVO);
    }

    @Override
    public List<ReceiveMemoVO> searchAllReciveMemo(SearchMemoVO searchMemoVO) {
        return getSqlSession().selectList(ReceiveMemoDao.NAME_SPACE + ".searchAllReciveMemo", searchMemoVO);
    }

    @Override
    public int insertNewReceiveMemo(List<ReceiveMemoVO> newReceiveMemoVOList) {
        return getSqlSession().insert(ReceiveMemoDao.NAME_SPACE + ".insertNewReceiveMemo", newReceiveMemoVOList);
    }

    @Override
    public ReceiveMemoVO selectOneReceiveMemo(String rcvMemoId) {
        return getSqlSession().selectOne(ReceiveMemoDao.NAME_SPACE + ".selectOneReceiveMemo", rcvMemoId);
    }

    @Override
    public List<String> searchReceiveMemoListBySendMemoId(String sendMemoId) {
        return getSqlSession().selectList(ReceiveMemoDao.NAME_SPACE + ".searchReceiveMemoListBySendMemoId", sendMemoId);
    }

    @Override
    public int updateReceiveMemoDate(String rcvMemoId) {
        return getSqlSession().update(ReceiveMemoDao.NAME_SPACE + ".updateReceiveMemoDate", rcvMemoId);
    }

    @Override
    public int updateSaveOneReceiveMemo(String rcvMemoId) {
        return getSqlSession().update(ReceiveMemoDao.NAME_SPACE + ".updateSaveOneReceiveMemo", rcvMemoId);
    }

    @Override
    public int deleteOneReceiveMemo(String rcvMemoId) {
        return getSqlSession().update(ReceiveMemoDao.NAME_SPACE + ".deleteOneReceiveMemo", rcvMemoId);
    }

    @Override
    public int deleteManyReceiveMemo(List<String> receiveMemoList) {
        return getSqlSession().update(ReceiveMemoDao.NAME_SPACE + ".deleteManyReceiveMemo", receiveMemoList);
    }
}
