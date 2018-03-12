package com.sg.service;

import java.util.List;

import com.sg.entity.Student;

public interface StudentService {
	
	void add(Student student);
	void update(Student student);
	void delete(int idStudent);
	
	List<Student> getAllByWorksheet(int worksheet_id);
	List<Student> getAllByDiscipline(int idDiscipline);
	List<Student> getAllByCourseAndSpecialty(int course, int idSpecialty);
	List<Student> getNotEnrolledStudentsByDisciplineAndCourseAndSpecialty(int course, int idSpecialty, int idDiscipline);
	
}
