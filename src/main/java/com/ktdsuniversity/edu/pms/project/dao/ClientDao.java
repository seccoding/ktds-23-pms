package com.ktdsuniversity.edu.pms.project.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.project.vo.ClientVO;

public interface ClientDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.project.dao.ClientDao";

	public int createClient(ClientVO clientVO);

	public List<ClientVO> getAllClient();

	public ClientVO getClientOfProject(String clntInfo);

	public int modifyClient(ClientVO clientVO);

}
