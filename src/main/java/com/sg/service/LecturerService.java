package com.sg.service;

import java.util.List;

import com.sg.entity.Lecturer;

public interface LecturerService {
	
	void add(Lecturer lecturer);
	void update(Lecturer lecturer);
	void delete(int idLecturer);
	
	List<Lecturer> getAllByWorksheet(int id_worksheet);
	List<Lecturer> getAllByDiscipline(int idDiscipline);
	
}
