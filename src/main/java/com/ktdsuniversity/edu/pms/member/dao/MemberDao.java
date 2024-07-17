package com.ktdsuniversity.edu.pms.member.dao;

import com.ktdsuniversity.edu.pms.member.vo.MemberVO;

public interface MemberDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.member.dao.MemberDao";

	public MemberVO getOneMember(String memId);

	public int createMember(MemberVO memberVO);

}
