package com.ktdsuniversity.edu.pms.member.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.member.vo.MemberVO;
import com.ktdsuniversity.edu.pms.member.vo.PhonePlanVO;

public interface MemberService {

	boolean createMember(MemberVO memberVO);

	List<PhonePlanVO> getAllPhonePlans();

}
