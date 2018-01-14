package com.sg.service;

import java.util.List;

import com.sg.entity.Schedule;

public interface ScheduleService {
	
	void generate(int idWorksheet);
	void deleteAllByWorksheet(int idWorksheet);
	
	List<Schedule> getAllByWorksheet(int idWorksheet);
	List<Schedule> getAllByCourseSpecialtyWorksheet(int course, int idSpecialty, int idWorksheet);

}
