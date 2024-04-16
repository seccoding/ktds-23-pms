package com.ktdsuniversity.edu.pms.department.dao;

<<<<<<< Updated upstream
=======
import java.util.List;

>>>>>>> Stashed changes
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
<<<<<<< Updated upstream

@Repository
public class DepartmentDaoImpl extends SqlSessionDaoSupport implements DepartmentDao {

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
=======

import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;

@Repository
public class DepartmentDaoImpl extends SqlSessionDaoSupport implements DepartmentDao{
>>>>>>> Stashed changes

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int getDepartmentCount() {
		return getSqlSession().selectOne(DepartmentDao.NAME_SPACE + ".getDepartmentCount");
	}
	
	@Override
	public List<DepartmentVO> getAllDepartment() {
		return getSqlSession().selectList(DepartmentDao.NAME_SPACE + ".getAllDepartment");
	}

	@Override
	public int createNewDepartment(DepartmentVO deparmentVO) {
		return getSqlSession().insert(DepartmentDao.NAME_SPACE + ".createNewDepartment");
	}

	@Override
	public int deleteOneDepartment(String id) {
		return getSqlSession().update(DepartmentDao.NAME_SPACE + ".deleteOneDepartment", id);
	}

	@Override
	public int updateOneDepartment(DepartmentVO departmentVO) {
		return getSqlSession().update(DepartmentDao.NAME_SPACE + ".updateOneDepartment", departmentVO);
	}

}
