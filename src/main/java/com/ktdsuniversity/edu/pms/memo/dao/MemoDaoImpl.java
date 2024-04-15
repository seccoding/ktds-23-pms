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

	/**
	 * 보낸 메일이 총 몇개인지 카운트
	 */
	@Override
	public int getSentMemoAllCount() {	
		return getSqlSessionTemplate().selectOne(MemoDao.NAME_SPACE + ".getSentMemoAllCount");
	}

	@Override
	public List<MemoVO> getSentAllMemo() {
		return getSqlSessionTemplate().selectList(MemoDao.NAME_SPACE + ".getSentAllMemo");
	}
	
}
