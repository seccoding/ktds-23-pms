package com.ktdsuniversity.edu.pms.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.member.vo.MemberVO;

@Repository
public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public MemberVO getOneMember(String memId) {
		return getSqlSession().selectOne(MemberDao.NAME_SPACE + ".getOneMember", memId);
	}

	@Override
	public int createMember(MemberVO memberVO) {
		return getSqlSession().insert(MemberDao.NAME_SPACE + ".createMember", memberVO);
	}

}