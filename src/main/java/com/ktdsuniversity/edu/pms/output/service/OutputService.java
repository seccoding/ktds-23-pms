package com.ktdsuniversity.edu.pms.output.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.output.vo.OutputListVO;
import com.ktdsuniversity.edu.pms.output.vo.OutputSearchVO;
import com.ktdsuniversity.edu.pms.output.vo.OutputVO;

public interface OutputService {

	public OutputListVO getAllOutputList();
	
	public OutputListVO serarchAllOutputList(OutputSearchVO outputSearchVO);

	public boolean insertOneOutput(OutputVO outputVO, MultipartFile file);

	public OutputVO getOneOutput(String outId);

	public ResponseEntity<Resource> getDownloadFile(OutputVO output);

	public boolean deleteOneOutput(String outId);

	public boolean updateOneOutput(OutputVO outputVO, MultipartFile file);
	
	

}
