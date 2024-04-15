package com.ktdsuniversity.edu.pms.review.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;

public interface ReviewDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.review.dao.ReviewDao";

	public List<ReviewVO> selectAllReview();

}
