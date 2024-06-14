package com.ktdsuniversity.edu.pms.issue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.beans.FileHandler.StoredFile;
import com.ktdsuniversity.edu.pms.issue.dao.IssueDao;
import com.ktdsuniversity.edu.pms.issue.vo.IssueListVO;
import com.ktdsuniversity.edu.pms.issue.vo.IssueVO;
import com.ktdsuniversity.edu.pms.issue.vo.SearchIssueVO;

@Service
public class IssueServiceImpl implements IssueService {

	@Autowired
	private IssueDao issueDao;
	
	@Autowired
	private FileHandler fileHandler;

	@Override
	public IssueListVO getAllIssue() {
		int issueCount = this.issueDao.getAllIssueCount();
		List<IssueVO> issueList = this.issueDao.getAllIssue();
		
		IssueListVO issueListVO = new IssueListVO();
		issueListVO.setIssueCnt(issueCount);
		issueListVO.setIssueList(issueList);
		
		return issueListVO;
	}
	
	@Transactional
	@Override
	public IssueListVO searchAllIssue(SearchIssueVO searchIssueVO) {
		int issueCount = this.issueDao.searchIssueAllCount(searchIssueVO);
		searchIssueVO.setPageCount(issueCount);
		
		List<IssueVO> issueList = this.issueDao.searchAllIssue(searchIssueVO);
		
		IssueListVO issueListVO = new IssueListVO();
		issueListVO.setIssueCnt(issueCount);
		issueListVO.setIssueList(issueList);
		
		return issueListVO;
	}

	@Transactional
	@Override
	public IssueVO getOneIssue(String isId, boolean isIncrease) {
		IssueVO issueVO = this.issueDao.selectOneIssue(isId);
		
		if (issueVO == null) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		if (isIncrease) {
			this.issueDao.increaseViewCount(isId);
		}
		return issueVO;
	}
	
	@Transactional
	@Override
	public boolean createNewIssue(IssueVO issueVO, MultipartFile file) {
		
		if (file != null && ! file.isEmpty()) {
			StoredFile storedFile = fileHandler.storeFile(file);
			
			if (storedFile != null) {
				issueVO.setFileName(storedFile.getRealFileName());
				issueVO.setOriginFileName(storedFile.getFileName());
			}
		}
		int insertedCount = this.issueDao.insertNewIssue(issueVO);
		
		return insertedCount > 0;
	}

	@Transactional
	@Override
	public boolean updateOneIssue(IssueVO issueVO, MultipartFile file) {
		if (file != null && ! file.isEmpty()) {
			IssueVO originalIssueVO = this.issueDao.selectOneIssue(issueVO.getIsId());
			if ( originalIssueVO != null ) {
				String storedFileName = originalIssueVO.getFileName();
				if (storedFileName != null && storedFileName.length() > 0) {
					this.fileHandler.deleteFileByFileName(storedFileName);
				}
			}
			StoredFile storedFile = this.fileHandler.storeFile(file);
			issueVO.setFileName(storedFile.getRealFileName());
			issueVO.setOriginFileName(storedFile.getFileName());
		}
		int updatedCount = this.issueDao.updateOneIssue(issueVO);
		
		return updatedCount > 0;
	}

	@Transactional
	@Override
	public boolean deleteOneIssue(String isId) {
		IssueVO originalIssueVO = this.issueDao.selectOneIssue(isId);
		if (originalIssueVO != null) {
			String storedFileName = originalIssueVO.getFileName();
			if (storedFileName != null && storedFileName.length() > 0) {
				this.fileHandler.deleteFileByFileName(storedFileName);
			}
		}
		int deletedCount = this.issueDao.deleteOneIssue(isId);
		
		return deletedCount > 0;
	}
	
	@Transactional
	@Override
	public boolean deleteManyIssue(List<String> deleteItems) {
		List<IssueVO> originalIssueList = this.issueDao.selectManyIssue(deleteItems);
		
		for (IssueVO issueVO: originalIssueList) {
			if (issueVO != null) {
				String storedFileName = issueVO.getFileName();
				if (storedFileName != null && storedFileName.length() > 0) {
					this.fileHandler.deleteFileByFileName(storedFileName);
				}
			}
		}
		int deletedCount = this.issueDao.deleteManyIssue(deleteItems);

		return deletedCount > 0;
	}

	@Override
	public List<IssueVO> searchIssueByPrjId(String prjId) {
		return this.issueDao.searchIssueByPrjId(prjId);
	}
}
