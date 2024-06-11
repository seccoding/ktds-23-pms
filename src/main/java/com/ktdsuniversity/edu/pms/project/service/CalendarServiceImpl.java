package com.ktdsuniversity.edu.pms.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.project.dao.CalendarDao;
import com.ktdsuniversity.edu.pms.project.vo.CalendarVO;

@Service
public class CalendarServiceImpl implements CalendarService{
	
	@Autowired
	private CalendarDao calendarDao;

	@Override
	public List<CalendarVO> getCalendarByPrjId(String prjId) {
		return this.calendarDao.getCalendarByPrjId(prjId);
	}

	@Override
	public boolean addCalendar(CalendarVO calendarVO) {
		return this.calendarDao.addCalendar(calendarVO) > 0;
	}

	@Override
	public boolean modifyCalendar(CalendarVO calendarVO) {
		return this.calendarDao.modifyCalendar(calendarVO) > 0;
	}

}
