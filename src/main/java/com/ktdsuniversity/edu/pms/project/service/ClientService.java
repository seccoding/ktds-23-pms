package com.ktdsuniversity.edu.pms.project.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.project.vo.ClientVO;

public interface ClientService {

	boolean createClient(ClientVO clientVO);

	List<ClientVO> getAllClient();

	ClientVO getClientOfProject(String clntInfo);

	boolean modifyClient(ClientVO clientVO);

}
