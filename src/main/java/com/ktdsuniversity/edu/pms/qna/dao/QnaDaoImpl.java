package com.ktdsuniversity.edu.pms.qna.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.knowledge.dao.KnowledgeDao;
import com.ktdsuniversity.edu.pms.qna.vo.QnaRecommendVO;
import com.ktdsuniversity.edu.pms.qna.vo.QnaVO;
import com.ktdsuniversity.edu.pms.qna.vo.SearchQnaVO;

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

	@Override
	public int insertNewQna(QnaVO qnaVO) {
		return getSqlSession().insert(QnaDao.NAME_SPACE + ".insertNewQna", qnaVO);
	}

	@Override
	public int updateOneQna(QnaVO qnaVO) {
		return getSqlSession().update(QnaDao.NAME_SPACE + ".updateOneQna", qnaVO);
	}
	
	@Override
	public int deleteOneQna(String qaId) {
		return getSqlSession().update(QnaDao.NAME_SPACE + ".deleteOneQna", qaId);
	}

	@Override
	public List<QnaVO> selectManyQna(List<String> deleteItems) {
		return getSqlSession().selectList(QnaDao.NAME_SPACE + ".selectManyQna", deleteItems);
	}

	@Override
	public int deleteManyQna(List<String> deleteItems) {
		return getSqlSession().update(QnaDao.NAME_SPACE + ".deleteManyQna", deleteItems);
	}

	@Override
	public int recommendOneQna(String qaId) {
		return getSqlSession().update(QnaDao.NAME_SPACE + ".recommendOneQna", qaId);
	}

	@Override
	public int searchAllQnaCount(SearchQnaVO searchQnaVO) {
		return getSqlSession().selectOne(QnaDao.NAME_SPACE + ".searchAllQnaCount", searchQnaVO);
	}

	@Override
	public List<QnaVO> searchAllQna(SearchQnaVO searchQnaVO) {
		return getSqlSession().selectList(QnaDao.NAME_SPACE + ".searchAllQna", searchQnaVO);
	}

	@Override
	public int insertOneRecommend(QnaRecommendVO qnaRecommendVO) {
		return getSqlSession().insert(QnaDao.NAME_SPACE + ".insertOneRecommend", qnaRecommendVO);
	}

	@Override
	public QnaRecommendVO selectOneRecommend(QnaRecommendVO qnaRecommendVO) {
		return getSqlSession().selectOne(QnaDao.NAME_SPACE + ".selectOneRecommend", qnaRecommendVO);
	}

	@Override
	public int selectOneRecommendCount(String qaId) {
		return getSqlSession().selectOne(QnaDao.NAME_SPACE + ".selectOneRecommendCount", qaId);
	}





	
	

}
