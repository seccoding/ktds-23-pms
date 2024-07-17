package com.ktdsuniversity.edu.pms.member.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.member.vo.PhonePlanVO;

public interface PhonePlanDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.member.dao.PhonePlanDao";

	List<PhonePlanVO> selectAllPhonePlans();

}
