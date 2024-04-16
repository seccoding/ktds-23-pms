package com.ktdsuniversity.edu.pms.output.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.output.dao.OutputDao;
import com.ktdsuniversity.edu.pms.output.vo.OutputListVO;
import com.ktdsuniversity.edu.pms.output.vo.OutputVO;

@Service
public class OutputServiceImpl implements OutputService{
	@Autowired
	private OutputDao outputDao;

	@Override
	public OutputListVO getAllOutputList() {
		OutputListVO outputList= new OutputListVO();
		outputList.setOutputList(this.outputDao.getAllOutputList());
		outputList.setListCnt(this.outputDao.getOutputCnt());
		return outputList;
	}

	@Override
	public boolean updateOneOutput(OutputVO outputVO) {
		return this.outputDao.updateOneOutput(outputVO)>0;
	}

	
	
	
	
	
}
