package com.ktdsuniversity.edu.pms.commoncode.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;

public interface CommonCodeService {

	public List<CommonCodeVO> getAllCommonCodeList();

	public List<CommonCodeVO> getAllCommonCodeListByPId(String pid);

}
