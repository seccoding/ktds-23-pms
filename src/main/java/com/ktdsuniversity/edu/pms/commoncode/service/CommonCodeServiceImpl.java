package com.ktdsuniversity.edu.pms.commoncode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.commoncode.dao.CommonCodeDao;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;

@Service
public class CommonCodeServiceImpl implements CommonCodeService {

	private static List<CommonCodeVO> COMMON_CODE_LIST;
	public static boolean NEED_RELOAD;
	private static Object LOCK;

	public static void needReload(boolean needReload) {
		NEED_RELOAD = needReload;
	}

	static {
		NEED_RELOAD = false;
		if (COMMON_CODE_LIST == null) {
			NEED_RELOAD = true;
		}

		LOCK = new Object();
	}

	@Autowired
	private CommonCodeDao commonCodeDao;

	@Override
	public List<CommonCodeVO> getAllCommonCodeList() {
		synchronized (LOCK) {
			if (NEED_RELOAD) {
				COMMON_CODE_LIST = commonCodeDao.selectAllCommonCodeList();
			}
		}

		return COMMON_CODE_LIST;
	}

	@Override
	public List<CommonCodeVO> getAllCommonCodeListByPId(String pid) {
		getAllCommonCodeList();

		return COMMON_CODE_LIST.stream()
				.filter(code -> code.getCmcdPid() != null)
				.filter(code -> code.getCmcdPid().equals(pid)).toList();

	}

	@Transactional
	@Override
	public boolean saveNewCommonCode(CommonCodeVO commonCodeVO) {
		int insertedCount = this.commonCodeDao
				.insertNewCommonCode(commonCodeVO);
		needReload(insertedCount > 0);
		return insertedCount > 0;
	}

	@Transactional
	@Override
	public boolean updateCommonCode(CommonCodeVO commonCodeVO) {
		int insertedCount = this.commonCodeDao.updateCommonCode(commonCodeVO);
		needReload(insertedCount > 0);
		return insertedCount > 0;
	}

	@Transactional
	@Override
	public boolean deleteCommonCode(CommonCodeVO commonCodeVO) {
		int insertedCount = this.commonCodeDao.deleteCommonCode(commonCodeVO);
		needReload(insertedCount > 0);
		return insertedCount > 0;
	}

}
