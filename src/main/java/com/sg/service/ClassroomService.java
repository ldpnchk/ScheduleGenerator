package com.sg.service;

import java.util.List;

import com.sg.entity.Classroom;

public interface ClassroomService {
	
	void addClassroom(Classroom classroom);
	List<Classroom> getAllWithToolsByWorksheet(int id_worksheet);

}
