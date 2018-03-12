package com.sg.repository;

import java.util.List;

import com.sg.entity.Student;

public interface StudentRepository {
	
	void add(Student student);
	void update(Student student);
	void delete(int idStudent);
	
	List<Student> getAllByWorksheet(int id_worksheet);
	List<Student> getAllByDiscipline(int idDiscipline);
	List<Student> getAllByCourseAndSpecialty(int course, int idSpecialty);
	List<Student> getNotEnrolledStudentsByDisciplineAndCourseAndSpecialty (int course, int idSpecialty, int idDiscipline);

}
