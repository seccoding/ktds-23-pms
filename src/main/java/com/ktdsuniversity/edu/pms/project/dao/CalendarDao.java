package com.ktdsuniversity.edu.pms.project.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.project.vo.CalendarVO;

public interface CalendarDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.project.dao.CalendarDao";

	public List<CalendarVO> getCalendarByPrjId(String prjId);

	public int addCalendar(CalendarVO calendarVO);

	public int modifyCalendar(CalendarVO calendarVO);

}
