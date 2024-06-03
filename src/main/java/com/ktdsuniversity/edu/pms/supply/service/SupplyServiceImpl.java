package com.ktdsuniversity.edu.pms.supply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.beans.FileHandler.StoredFile;
import com.ktdsuniversity.edu.pms.supply.dao.SupplyDao;
import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyVO;

@Service
public class SupplyServiceImpl implements SupplyService {
	
	@Autowired
	private SupplyDao supplyDao;
	
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

	@Transactional
	@Override
	public boolean registerNewSupply(SupplyVO supplyVO, MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			StoredFile storedFile = fileHandler.storeFile(file);
			
			if (storedFile != null) {
				supplyVO.setSplImg(storedFile.getRealFilePath());
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

}
