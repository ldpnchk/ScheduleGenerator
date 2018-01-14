package com.sg.repository;

import java.util.List;

import com.sg.entity.Schedule;

public interface ScheduleRepository {
	
	void add(Schedule schedule);
	void deleteAllByWorksheet(int idWorksheet);
	
	List<Schedule> getAllByWorksheet(int idWorksheet);
	List<Schedule> getAllByCourseSpecialtyWorksheet(int course, int idSpecialty, int idWorksheet);
	
}
