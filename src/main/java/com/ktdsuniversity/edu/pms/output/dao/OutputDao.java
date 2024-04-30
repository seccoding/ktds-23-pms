package com.ktdsuniversity.edu.pms.output.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.output.vo.OutputSearchVO;
import com.ktdsuniversity.edu.pms.output.vo.OutputVO;

public interface OutputDao {
	
	String NAME_SPACE = "com.ktdsuniversity.edu.pms.output.dao.OutputDao";

	public List<OutputVO> getAllOutputList();
	
	
	public List<OutputVO> searchAllOutPutList(OutputSearchVO outputSearchVO);
	
	public int getOutputCnt ();

	public int searchOutputCnt (OutputSearchVO outputSearchVO);
	

	public int insertOneOutput(OutputVO outputVO);

	public OutputVO getOneOutput(String outId);

	public int deleteOneOutput(String outId);

	public int updateOneOutput(OutputVO outputVO);

	public OutputVO getOneOutputByPoutId(String outPId); 
	
}
