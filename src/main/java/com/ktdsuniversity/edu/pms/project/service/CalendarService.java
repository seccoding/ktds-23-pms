package com.ktdsuniversity.edu.pms.project.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.project.vo.CalendarVO;

public interface CalendarService {

	List<CalendarVO> getCalendarByPrjId(String prjId);

	boolean addCalendar(CalendarVO calendarVO);

	boolean modifyCalendar(CalendarVO calendarVO);

}
