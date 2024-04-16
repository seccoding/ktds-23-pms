package com.ktdsuniversity.edu.pms.output.service;

import com.ktdsuniversity.edu.pms.output.vo.OutputListVO;
import com.ktdsuniversity.edu.pms.output.vo.OutputVO;

public interface OutputService {

	public OutputListVO getAllOutputList();

	public boolean updateOneOutput(OutputVO outputVO);
	
	

}
