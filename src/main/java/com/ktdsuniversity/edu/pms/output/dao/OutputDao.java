package com.ktdsuniversity.edu.pms.output.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.output.vo.OutputVO;

public interface OutputDao {
	
	String NAME_SPACE = "com.ktdsuniversity.edu.pms.output.dao.OutputDao";

	public List<OutputVO> getAllOutputList();
	
	public int getOutputCnt ();

	public int updateOneOutput(OutputVO outputVO); 
	
}
