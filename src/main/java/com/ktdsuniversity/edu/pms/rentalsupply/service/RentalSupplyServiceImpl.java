package com.ktdsuniversity.edu.pms.rentalsupply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.rentalsupply.dao.RentalSupplyDao;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyListVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.SearchRentalSupplyVO;

@Service
public class RentalSupplyServiceImpl implements RentalSupplyService{
	
	@Autowired
	private RentalSupplyDao rentalSupplyDao;

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
}
