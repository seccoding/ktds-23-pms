package com.ktdsuniversity.edu.pms.commoncode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.commoncode.dao.CommonCodeDao;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;

@Service
public class CommonCodeServiceImpl implements CommonCodeService {

	@Autowired
	private CommonCodeDao commonCodeDao;

	@Override
	public List<CommonCodeVO> getAllCommonCodeList() {
		return commonCodeDao.selectAllCommonCodeList();
	}

}
