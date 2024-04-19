package com.ktdsuniversity.edu.pms.qna.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.knowledge.dao.KnowledgeDao;
import com.ktdsuniversity.edu.pms.qna.vo.QnaVO;

@Repository
public class QnaDaoImpl extends SqlSessionDaoSupport implements QnaDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate (SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int getAllQnaCount() {
		return getSqlSession().selectOne(QnaDao.NAME_SPACE + ".getAllQnaCount");
	}

	@Override
	public List<QnaVO> getAllQna() {
		return getSqlSession().selectList(QnaDao.NAME_SPACE + ".getAllQna");
	}

	@Override
	public QnaVO selectOneQna(String qaId) {
		return getSqlSession().selectOne(QnaDao.NAME_SPACE + ".selectOneQna", qaId);
	}

	@Override
	public int increaseViewCount(String qaId) {
		return getSqlSession().update(QnaDao.NAME_SPACE + ".increaseViewCount", qaId);
	}
	
	

}
