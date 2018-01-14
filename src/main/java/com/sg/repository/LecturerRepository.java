package com.sg.repository;

import java.util.List;

import com.sg.entity.Lecturer;

public interface LecturerRepository {
	
	void add(Lecturer lecturer);
	void update(Lecturer lecturer);
	void delete(int idLecturer);
	
	List<Lecturer> getAll();
	List<Lecturer> getAllByDiscipline(int idDiscipline);

}
