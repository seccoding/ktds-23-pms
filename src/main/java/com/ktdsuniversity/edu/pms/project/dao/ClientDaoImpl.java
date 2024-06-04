package com.ktdsuniversity.edu.pms.project.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.project.vo.ClientVO;

@Repository
public class ClientDaoImpl extends SqlSessionDaoSupport  implements ClientDao {
	
	 	@Autowired
	    @Override
	    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
	        super.setSqlSessionTemplate(sqlSessionTemplate);
	    }

		@Override
		public int createClient(ClientVO clientVO) {
			return getSqlSession().insert(ClientDao.NAME_SPACE+".createClient",clientVO);
		}

}
