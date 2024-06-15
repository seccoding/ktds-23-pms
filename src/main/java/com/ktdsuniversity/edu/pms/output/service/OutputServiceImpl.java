package com.ktdsuniversity.edu.pms.output.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.beans.FileHandler.StoredFile;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.output.dao.OutputDao;
import com.ktdsuniversity.edu.pms.output.vo.OutputListVO;
import com.ktdsuniversity.edu.pms.output.vo.OutputSearchVO;
import com.ktdsuniversity.edu.pms.output.vo.OutputVO;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

@Service
public class OutputServiceImpl implements OutputService {
	@Autowired
	private OutputDao outputDao;

	@Autowired
	private FileHandler fileHandler;

	@Override
	public OutputListVO getAllOutputList() {
		OutputListVO outputList = new OutputListVO();
		outputList.setOutputList(this.outputDao.getAllOutputList());
		outputList.setListCnt(this.outputDao.getOutputCnt());
		return outputList;
	}

	@Override
	public OutputListVO serarchAllOutputList(OutputSearchVO outputSearchVO) {
		OutputListVO outputList = new OutputListVO();
		outputList.setListCnt(this.outputDao.searchOutputCnt(outputSearchVO));
		outputSearchVO.setPageCount(outputList.getListCnt());
		outputList.setOutputList(this.outputDao.searchAllOutPutList(outputSearchVO));
		return outputList;
	}

	@Transactional
	@Override
	public boolean insertOneOutput(OutputVO outputVO, MultipartFile file) {
		OutputSearchVO outputSearchVO = new OutputSearchVO(outputVO.getPrjId(), outputVO.getOutType(),
				outputVO.getOutVer());
		List<OutputVO> outputList =this.outputDao.searchAllOutPutList(outputSearchVO);
		int size =outputList.size();
		if(size > 0) { //부모가 있을경우 부모를 지정
		outputVO.setOutVerNum(
				outputList.stream()
				.filter(output -> output.getLevel() == size)
				.toList()
				.get(0).getOutId());
		}
				
		if (file != null && !file.isEmpty()) {// 파일 존재
			StoredFile storedFile = fileHandler.storeFile(file);
			System.out.println("fileName========> " + storedFile.getFileName());
			System.out.println("realFileName========> " + storedFile.getRealFileName());
			if (storedFile != null) {
				outputVO.setOutFile(storedFile.getFileName());
				outputVO.setOutEncodeFile(storedFile.getRealFileName());
			
			}
		} else {
			throw new PageNotFoundException();
		}

		return this.outputDao.insertOneOutput(outputVO) > 0;
	}

	@Override
	public OutputVO getOneOutput(String outId) {

		return this.outputDao.getOneOutput(outId);
	}

	@Override
	public ResponseEntity<Resource> getDownloadFile(OutputVO output) {

		ResponseEntity<Resource> downloadFile = this.fileHandler.download(output.getOutFile(),
				output.getOutEncodeFile());
//		TODO 잘못된 이름이 들어올 경우?
//		if(downloadFile == null) {
//			 
//		}

		return downloadFile;
	}

	@Transactional
	@Override
	public boolean deleteOneOutput(String outId) {

		OutputVO output = this.outputDao.getOneOutput(outId);
		
		OutputVO nextOutput =this.outputDao.getOneOutputByPoutId(outId);
		if(nextOutput != null) {
			
		nextOutput.setOutVerNum(output.getOutVerNum());
		this.outputDao.updateOneOutput(nextOutput);
		}
		
		// 산출물은 파일이 필수요소라서 있는지 체크 x
		this.fileHandler.deleteFileByFileName(output.getOutEncodeFile());
		
		
		return this.outputDao.deleteOneOutput(outId) > 0;
	}

	@Transactional
	@Override
	public boolean updateOneOutput(OutputVO outputVO, MultipartFile file) {
		if (file != null && !file.isEmpty()) {// 파일 존재
			StoredFile storedFile = fileHandler.storeFile(file);
			if (storedFile != null) {
				outputVO.setOutFile(storedFile.getFileName());
				outputVO.setOutEncodeFile(storedFile.getRealFileName());
			}
		} else {
			OutputVO thisOutput = this.outputDao.getOneOutput(outputVO.getOutId());
			outputVO.setOutFile(thisOutput.getOutFile());
			outputVO.setOutEncodeFile(thisOutput.getOutEncodeFile());
		}

		return this.outputDao.updateOneOutput(outputVO) > 0;
	}

}
