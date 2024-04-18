package com.ktdsuniversity.edu.pms.memo.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.memo.vo.MemoVO;

@Repository
public class MemoDaoImpl extends SqlSessionDaoSupport implements MemoDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int getSentMemoAllCount() {
		return getSqlSessionTemplate().selectOne(MemoDao.NAME_SPACE + ".getSentMemoAllCount");
	}

	@Override
	public List<MemoVO> getAllSentMemo() {
		return getSqlSessionTemplate().selectList(MemoDao.NAME_SPACE + ".getAllSentMemo");
	}

	@Override
	public int getStorageMemoAllCount() {
		return getSqlSessionTemplate().selectOne(MemoDao.NAME_SPACE + ".getStorageMemoAllCount");
	}

	@Override
	public List<MemoVO> getAllStorageMemo() {
		return getSqlSessionTemplate().selectList(MemoDao.NAME_SPACE + ".getAllStorageMemo");
	}

	@Override
	public int getReceiveMemoAllCount() {
		return getSqlSessionTemplate().selectOne(MemoDao.NAME_SPACE + ".getReceiveMemoAllCount");
	}

	@Override
	public List<MemoVO> getAllReceiveMemo() {
		return getSqlSessionTemplate().selectList(MemoDao.NAME_SPACE + ".getAllReceiveMemo");
	}
	
	@Override
	public int writeNewMemo(MemoVO memoVO) {
		return getSqlSessionTemplate().insert(MemoDao.NAME_SPACE + ".writeNewMemo", memoVO);
	}

	@Override
	public MemoVO selectOneMemo(String memoId) {
		return getSqlSessionTemplate().selectOne(MemoDao.NAME_SPACE + ".selectOneMemo", memoId);
	}

	@Override
	public int changeViewStatus(String memoId) {
		return getSqlSessionTemplate().update(MemoDao.NAME_SPACE + ".changeViewStatus", memoId);
	}

	@Override
	public int deleteOneMemo(String memoId) {
		return getSqlSessionTemplate().update(MemoDao.NAME_SPACE + ".deleteOneMemo", memoId);
	}

	@Override
	public List<MemoVO> selectManyMemo(List<String> Items) {
		return getSqlSessionTemplate().selectList(MemoDao.NAME_SPACE + ".selectManyMemo", Items);
	}

	@Override
	public int deleteManyMemo(List<String> Items) {
		return getSqlSessionTemplate().update(MemoDao.NAME_SPACE + ".deleteManyMemo", Items);
	}

	
	
}
