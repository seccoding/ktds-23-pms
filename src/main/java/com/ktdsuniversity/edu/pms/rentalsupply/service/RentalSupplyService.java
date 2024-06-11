package com.ktdsuniversity.edu.pms.rentalsupply.service;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyListVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.SearchRentalSupplyVO;

public interface RentalSupplyService {

	public RentalSupplyListVO searchAllRentalSupply(SearchRentalSupplyVO searchRentalSupplyVO);

	public RentalSupplyVO getOneRentalSupply(String rsplId);
	
	public boolean registerNewRentalSupply(RentalSupplyVO rentalSupplyVO, MultipartFile file);
	
	public boolean updateOneRentalSupply(RentalSupplyVO rentalSupplyVO);
	
	public boolean updateOneRentalSupplyStock(RentalSupplyVO rentalSupplyVO);
	
	public boolean deleteOneRentalSupply(RentalSupplyVO rentalSupplyVO);
	
}
