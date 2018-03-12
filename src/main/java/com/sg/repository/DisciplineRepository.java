package com.sg.repository;

import java.util.List;

import com.sg.entity.Discipline;

public interface DisciplineRepository {
	
	void add(Discipline discipline);
	void update(Discipline discipline);
	void delete(int idDiscipline);
	
	void addLecturer(int idDiscipline, int idLecturer);
	void addStudent(int idDiscipline, int idStudent);
	void removeLecturer(int idDiscipline, int idLecturer);
	void removeStudent(int idDiscipline, int idStudent);
	
	List<Discipline> getAllByLecturer(int idLecturer);
	List<Discipline> getAllByStudent(int idStudent);
	List<Discipline> getAllByWorksheet(int id_worksheet);

}
