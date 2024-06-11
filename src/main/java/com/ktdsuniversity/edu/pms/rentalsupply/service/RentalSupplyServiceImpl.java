package com.ktdsuniversity.edu.pms.rentalsupply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.beans.FileHandler.StoredFile;
import com.ktdsuniversity.edu.pms.rentalsupply.dao.RentalSupplyDao;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyListVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.SearchRentalSupplyVO;

@Service
public class RentalSupplyServiceImpl implements RentalSupplyService{
	
	@Autowired
	private RentalSupplyDao rentalSupplyDao;
	
	@Autowired
	private FileHandler fileHandler;

	@Override
	public RentalSupplyListVO searchAllRentalSupply(SearchRentalSupplyVO searchRentalSupplyVO) {
		int rentalSupplyCount = this.rentalSupplyDao.searchRentalSupplyAllCount(searchRentalSupplyVO);
		searchRentalSupplyVO.setPageCount(rentalSupplyCount);
		
		List<RentalSupplyVO> rentalSupplyList = this.rentalSupplyDao.searchAllRentalSupply(searchRentalSupplyVO);
		
		RentalSupplyListVO rentalSupplyListVO = new RentalSupplyListVO();
		rentalSupplyListVO.setRentalSupplyCnt(rentalSupplyCount);
		rentalSupplyListVO.setRentalSupplyList(rentalSupplyList);
		
		return rentalSupplyListVO;
	}

	@Override
	public RentalSupplyVO getOneRentalSupply(String rsplId) {
		RentalSupplyVO rentalSupplyVO = this.rentalSupplyDao.selectOneRentalSupply(rsplId);
		
		return rentalSupplyVO;
	}

	@Transactional
	@Override
	public boolean registerNewRentalSupply(RentalSupplyVO rentalSupplyVO, MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			StoredFile storedFile = fileHandler.storeFile(file);
			
			if (storedFile != null) {
				rentalSupplyVO.setRsplImg(storedFile.getRealFileName());
			}
		}
		
		int registeredCount = this.rentalSupplyDao.registerNewRentalSupply(rentalSupplyVO);
		
		return registeredCount > 0;
	}

	@Transactional
	@Override
	public boolean updateOneRentalSupply(RentalSupplyVO rentalSupplyVO) {
		int updatedCount = this.rentalSupplyDao.updateOneRentalSupply(rentalSupplyVO);
		
		return updatedCount > 0;
	}

	@Transactional
	@Override
	public boolean updateOneRentalSupplyStock(RentalSupplyVO rentalSupplyVO) {
		int updatedCount = this.rentalSupplyDao.updateOneRentalSupplyStock(rentalSupplyVO);
		
		return updatedCount > 0;
	}

	@Transactional
	@Override
	public boolean deleteOneRentalSupply(RentalSupplyVO rentalSupplyVO) {
		RentalSupplyVO originalRentalSupplyVO = this.rentalSupplyDao.selectOneRentalSupply(rentalSupplyVO.getRsplId());
		
		if (originalRentalSupplyVO != null) {
			String storedImage = originalRentalSupplyVO.getRsplImg();
			
			if (storedImage != null && storedImage.length() > 0) {
				this.fileHandler.deleteFileByFileName(storedImage);
			}
		}
		
		int deletedCount = this.rentalSupplyDao.deleteOneRentalSupply(rentalSupplyVO);
		
		return deletedCount > 0;
	}
}
