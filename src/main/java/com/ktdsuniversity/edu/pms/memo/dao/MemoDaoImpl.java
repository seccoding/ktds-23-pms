package com.ktdsuniversity.edu.pms.memo.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.memo.vo.MemoVO;
import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;

@Repository
public class MemoDaoImpl extends SqlSessionDaoSupport implements MemoDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int getSentMemoAllCount(SearchMemoVO searchMemoVO) {
		return getSqlSessionTemplate().selectOne(MemoDao.NAME_SPACE + ".getSentMemoAllCount" , searchMemoVO);
	}

	@Override
	public List<MemoVO> getAllSentMemo(SearchMemoVO searchMemoVO) {
		return getSqlSessionTemplate().selectList(MemoDao.NAME_SPACE + ".getAllSentMemo", searchMemoVO);
	}

	@Override
	public int getStorageMemoAllCount(SearchMemoVO searchMemoVO) {
		return getSqlSessionTemplate().selectOne(MemoDao.NAME_SPACE + ".getStorageMemoAllCount", searchMemoVO);
	}

	@Override
	public List<MemoVO> getAllStorageMemo(SearchMemoVO searchMemoVO) {
		return getSqlSessionTemplate().selectList(MemoDao.NAME_SPACE + ".getAllStorageMemo", searchMemoVO);
	}

	@Override
	public int getReceiveMemoAllCount(SearchMemoVO searchMemoVO) {
		return getSqlSessionTemplate().selectOne(MemoDao.NAME_SPACE + ".getReceiveMemoAllCount", searchMemoVO);
	}

	@Override
	public List<MemoVO> getAllReceiveMemo(SearchMemoVO searchMemoVO) {
		return getSqlSessionTemplate().selectList(MemoDao.NAME_SPACE + ".getAllReceiveMemo", searchMemoVO);
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

	@Override
	public int saveOneMemo(MemoVO memoVO) {
		return getSqlSessionTemplate().update(MemoDao.NAME_SPACE + ".saveOneMemo", memoVO);
	}

	@Override
	public int getReceiveMemoReadYCount(SearchMemoVO searchMemoVO) {
		return getSqlSessionTemplate().selectOne(MemoDao.NAME_SPACE + ".getReceiveMemoReadYCount" , searchMemoVO);
	}

	@Override
	public List<MemoVO> getReadYReceiveMemo(SearchMemoVO searchMemoVO) {
		return getSqlSessionTemplate().selectList(MemoDao.NAME_SPACE + ".getReadYReceiveMemo" , searchMemoVO);	
	}

	@Override
	public MemoVO findMemo(String memoId) {
		return getSqlSessionTemplate().selectOne(MemoDao.NAME_SPACE + ".findMemo" , memoId);	
	}

}
