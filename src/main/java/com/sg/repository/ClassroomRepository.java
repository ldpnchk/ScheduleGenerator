package com.sg.repository;

import java.util.List;

import com.sg.entity.Classroom;

public interface ClassroomRepository {
	
	List<Classroom> getAllWithToolsByWorksheet(int id_worksheet);

	void addClassroom(Classroom classroom);

}
