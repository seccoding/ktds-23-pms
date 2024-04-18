package com.ktdsuniversity.edu.pms.commoncode.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;

public interface CommonCodeDao {

	public static final String NAME_SPACE = "com.ktdsuniversity.edu.pms.commoncode.dao.CommonCodeDao";

	public List<CommonCodeVO> selectAllCommonCodeList();

	public int insertNewCommonCode(CommonCodeVO commonCodeVO);

	public int updateCommonCode(CommonCodeVO commonCodeVO);

	public int deleteCommonCode(CommonCodeVO commonCodeVO);

}
