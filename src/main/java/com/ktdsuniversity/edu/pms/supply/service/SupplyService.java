package com.ktdsuniversity.edu.pms.supply.service;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyLogListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyVO;

public interface SupplyService {

	public SupplyListVO searchAllSupply(SearchSupplyVO searchSupplyVO);

	public SupplyVO getOneSupply(String splId);
	
//	public File getSupplyImage(String splImg);

	public boolean registerNewSupply(SupplyVO supplyVO, MultipartFile file);

	public boolean updateOneSupply(SupplyVO supplyVO);

	public boolean deleteOneSupply(SupplyVO supplyVO);

	public SupplyLogListVO searchAllSupplyLog(SearchSupplyVO searchSupplyVO);

}
