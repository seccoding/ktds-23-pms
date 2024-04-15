package com.ktdsuniversity.edu.pms.requirement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.beans.FileHandler.StoredFile;
import com.ktdsuniversity.edu.pms.requirement.dao.RequirementDao;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.requirement.vo.DelayAcessVO;

@Service
public class RequirementServiceImpl implements RequirementService{
	
	@Autowired
	private RequirementDao requirementDao;
	@Autowired
	private FileHandler fileHandler;
	@Override
	public List<RequirementVO> getAllRequirement() {
		return this.requirementDao.getAllRequirement();
		 
	}

	@Override
	public RequirementVO getOneRequirement(String rqmId) {
		return this.requirementDao.getOneRequirement(rqmId);
	}
	
	@Override
	public boolean insertOneRequirement(RequirementVO requirementVO, MultipartFile file) {
		// TODO 파일 네임 저장 복호화 & 실제내임 2개 필요
//		파일이 잇다면
		if( file !=null &&! file.isEmpty() ) {
			StoredFile storedFile = fileHandler.storeFile(file);
			if(storedFile !=null) {
				requirementVO.setRqmFile(storedFile.getRealFileName());
			}
		}
		
		return this.requirementDao.insertOneRequirement(requirementVO)>0;
		
	}
	@Transactional
	@Override
	public boolean updateRequirement(RequirementVO requirementVO,  MultipartFile file) {
		// TODO Auto-generated method stub
//		파일이 잇다면
		if(! file.isEmpty() && ! file.equals(null)) {
			StoredFile storedFile = fileHandler.storeFile(file);
			if(storedFile !=null) {
				requirementVO.setRqmFile(storedFile.getRealFileName());
			}
		}
		return this.requirementDao.updateOneRequirement(requirementVO)>0;
		
		 

	}
	@Transactional
	@Override
	public boolean updateDelayRequirement(String rqmId, boolean isApprove) {
		
		DelayAcessVO delayAcessVO = (DelayAcessVO) this.requirementDao.getOneRequirement(rqmId);
		delayAcessVO.setApprove(isApprove);
		
		
		return this.requirementDao.updateDelayOneRequirement(delayAcessVO)>0;

		 
	}

	@Override
	public boolean deleteOneRequirement(RequirementVO RequirementVO) {
		
		
		return this.requirementDao.deleteReRequirement(RequirementVO)>0;
	}

	@Override
	public boolean delayRequirement(RequirementVO requirementVO) {

		return this.requirementDao.delayRequirement(requirementVO)>0;
	}

	

}
