package com.ktdsuniversity.edu.pms.supply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.beans.FileHandler.StoredFile;
import com.ktdsuniversity.edu.pms.supply.dao.SupplyDao;
import com.ktdsuniversity.edu.pms.supply.dao.SupplyLogDao;
import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyLogListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyLogVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyVO;

@Service
public class SupplyServiceImpl implements SupplyService {
	
	@Autowired
	private SupplyDao supplyDao;
	
	@Autowired
	private SupplyLogDao supplyLogDao;
	
	@Autowired
	private FileHandler fileHandler;

	@Override
	public SupplyListVO searchAllSupply(SearchSupplyVO searchSupplyVO) {
		int supplyCount = this.supplyDao.searchSupplyAllCount(searchSupplyVO);
		searchSupplyVO.setPageCount(supplyCount);
		
		List<SupplyVO> supplyList = this.supplyDao.searchAllSupply(searchSupplyVO);
		
		SupplyListVO supplyListVO = new SupplyListVO();
		supplyListVO.setSupplyCnt(supplyCount);
		supplyListVO.setSupplyList(supplyList);
		
		return supplyListVO;
	}

	@Override
	public SupplyVO getOneSupply(String splId) {
		SupplyVO supplyVO = this.supplyDao.selectOneSupply(splId);
		
		return supplyVO;
	}
	
//	@Override
//	public File getSupplyImage(String splImg) {
//		File file = fileHandler.getStoredFile(splImg);
//		
//		return file;
//	}

	@Transactional
	@Override
	public boolean registerNewSupply(SupplyVO supplyVO, MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			StoredFile storedFile = fileHandler.storeFile(file);
			
			if (storedFile != null) {
				supplyVO.setSplImg(storedFile.getRealFileName());
			}
		}
		
		int registeredCount = this.supplyDao.registerNewSupply(supplyVO);
		
		return registeredCount > 0;
	}

	@Transactional
	@Override
	public boolean updateOneSupply(SupplyVO supplyVO) {
		int updatedCount = this.supplyDao.updateOneSupply(supplyVO);
		
		return updatedCount > 0;
	}
	
	@Transactional
	@Override
	public boolean updateOneSupplyStock(SupplyVO supplyVO) {
		int updatedCount = this.supplyDao.updateOneSupplyStock(supplyVO);
		
		return updatedCount > 0;
	}

	@Transactional
	@Override
	public boolean deleteOneSupply(SupplyVO supplyVO) {
		SupplyVO originalSupplyVO = this.supplyDao.selectOneSupply(supplyVO.getSplId());
		
		if (originalSupplyVO != null) {
			String storedImage = originalSupplyVO.getSplImg();
			
			if (storedImage != null && storedImage.length() > 0) {
				this.fileHandler.deleteFileByFileName(storedImage);
			}
		}
		
		int deletedCount = this.supplyDao.deleteOneSupply(supplyVO);
		
		return deletedCount > 0;
	}

	@Override
	public SupplyLogListVO searchAllSupplyLog(SearchSupplyVO searchSupplyVO) {
		int supplyLogCount = this.supplyLogDao.searchSupplyLogAllCount(searchSupplyVO);
		searchSupplyVO.setPageCount(supplyLogCount);
		
		List<SupplyLogVO> supplyLogList = this.supplyLogDao.searchAllSupplyLog(searchSupplyVO);
		
		SupplyLogListVO supplyLogListVO = new SupplyLogListVO();
		supplyLogListVO.setSupplyLogCnt(supplyLogCount);
		supplyLogListVO.setSupplyLogList(supplyLogList);
		
		return supplyLogListVO;
	}

}
