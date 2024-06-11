package com.ktdsuniversity.edu.pms.project.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.project.vo.CalendarVO;

@Repository
public class CalendarDaoImpl extends SqlSessionDaoSupport  implements CalendarDao {
	
	@Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public List<CalendarVO> getCalendarByPrjId(String prjId) {
		return getSqlSession().selectList(CalendarDao.NAME_SPACE+".getCalendarByPrjId", prjId);
	}

	@Override
	public int addCalendar(CalendarVO calendarVO) {
		return getSqlSession().insert(CalendarDao.NAME_SPACE+".addCalendar", calendarVO);
	}

	@Override
	public int modifyCalendar(CalendarVO calendarVO) {
		return getSqlSession().update(CalendarDao.NAME_SPACE+".modifyCalendar", calendarVO);
	}

}
