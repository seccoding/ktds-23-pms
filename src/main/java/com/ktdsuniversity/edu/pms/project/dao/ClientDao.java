package com.ktdsuniversity.edu.pms.project.dao;

import com.ktdsuniversity.edu.pms.project.vo.ClientVO;

public interface ClientDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.project.dao.ClientDao";

	public int createClient(ClientVO clientVO);

}
