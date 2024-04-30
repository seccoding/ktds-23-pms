package com.ktdsuniversity.edu.pms.requirement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.beans.FileHandler.StoredFile;
import com.ktdsuniversity.edu.pms.requirement.dao.RequirementDao;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.requirement.vo.DelayAcessVO;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementListVO;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementSearchVO;

@Service
public class RequirementServiceImpl implements RequirementService {

	@Autowired
	private RequirementDao requirementDao;
	@Autowired
	private FileHandler fileHandler;

	@Override
	public List<RequirementVO> getAllRequirement() {

		return this.requirementDao.getAllRequirement();
	}

	@Override
	public List<RequirementVO> getAllRequirement(String prjId) {

		return this.requirementDao.getAllRequirementByprjId(prjId);
	}

	@Override
	public RequirementListVO searchAllRequirement(RequirementSearchVO requirementSearchVO) {

		RequirementListVO list = new RequirementListVO();
		list.setCount(this.requirementDao.getAllCount(requirementSearchVO));
		list.setRequirementList(this.requirementDao.searchAllRequirement(requirementSearchVO));
		return list;
	}

	@Override
	public RequirementVO getOneRequirement(String rqmId) {
		return this.requirementDao.getOneRequirement(rqmId);
	}

	@Transactional
	@Override
	public boolean insertOneRequirement(RequirementVO requirementVO, MultipartFile file) {
		// TODO 파일 네임 저장 복호화 & 실제내임 2개 필요
//		파일이 잇다면
		if (file != null && !file.isEmpty()) {
			StoredFile storedFile = fileHandler.storeFile(file);
			if (storedFile != null) {
				requirementVO.setRqmFile(storedFile.getFileName());
				requirementVO.setRqmEncodeFile(storedFile.getRealFileName());
			}
		}

		return this.requirementDao.insertOneRequirement(requirementVO) > 0;

	}

	@Transactional
	@Override
	public boolean updateRequirement(RequirementVO requirementVO, MultipartFile file) {
		// TODO Auto-generated method stub
//		파일이 잇다면
		if (file != null && !file.isEmpty()) {
			StoredFile storedFile = fileHandler.storeFile(file);
			if (storedFile != null) {
				requirementVO.setRqmFile(storedFile.getFileName());
				requirementVO.setRqmEncodeFile(storedFile.getRealFileName());
			}
		}
		return this.requirementDao.updateOneRequirement(requirementVO) > 0;

	}

	@Transactional
	@Override
	public boolean updateDelayRequirement(String rqmId, boolean isApprove) {
		// TODO 올바른 요구사항인지 체크 필요
		RequirementVO thisVO = this.requirementDao.getOneRequirement(rqmId);

		DelayAcessVO delayAcessVO = new DelayAcessVO();
		delayAcessVO.setRqmId(rqmId);
		delayAcessVO.setApprove(isApprove);

		return this.requirementDao.updateDelayOneRequirement(delayAcessVO) > 0;
	}

	@Transactional
	@Override
	public boolean deleteOneRequirement(RequirementVO RequirementVO) {
		if (RequirementVO.getRqmEncodeFile() != null && !RequirementVO.getRqmEncodeFile().equals("")) {
			this.fileHandler.deleteFileByFileName(RequirementVO.getRqmEncodeFile());
		}

		return this.requirementDao.deleteReRequirement(RequirementVO) > 0;
	}

	@Transactional
	@Override
	public boolean delayRequirement(RequirementVO requirementVO) {

		return this.requirementDao.delayRequirement(requirementVO) > 0;
	}

	@Transactional
	@Override
	public ResponseEntity<Resource> getDownloadFile(RequirementVO requirement) {
		ResponseEntity<Resource> downloadFile = this.fileHandler.download(requirement.getRqmFile(),
				requirement.getRqmEncodeFile());
		return downloadFile;
	}

	@Override
	public List<RequirementVO> getAllRequirementByTeammateId(String empId) {
		return this.requirementDao.getAllRequirementByTeammateId(empId);
	}

	@Transactional
	@Override
	public boolean updateTestResult(RequirementVO requirementVO, boolean testApprove) {

		if (testApprove) {
			requirementVO.setTestRslt("true");
		} else {
			requirementVO.setTestRslt("false");
		}

		return this.requirementDao.updateTestResult(requirementVO) > 0;
	}

}
