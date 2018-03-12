package com.sg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.entity.Student;
import com.sg.repository.StudentRepository;
import com.sg.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void add(Student student) {
		studentRepository.add(student);
	}

	@Override
	public void update(Student student) {
		studentRepository.update(student);
	}

	@Override
	public void delete(int idStudent) {
		studentRepository.delete(idStudent);
	}
	
	@Override
	public List<Student> getAllByWorksheet(int id_worksheet) {
		return studentRepository.getAllByWorksheet(id_worksheet);
	}
	
	@Override
	public List<Student> getAllByDiscipline(int idDiscipline) {
		return studentRepository.getAllByDiscipline(idDiscipline);
	}

	@Override
	public List<Student> getAllByCourseAndSpecialty(int course, int idSpecialty) {
		return studentRepository.getAllByCourseAndSpecialty(course, idSpecialty);
	}

	@Override
	public List<Student> getNotEnrolledStudentsByDisciplineAndCourseAndSpecialty(int course, int idSpecialty, int idDiscipline) {
		return studentRepository.getNotEnrolledStudentsByDisciplineAndCourseAndSpecialty(course, idSpecialty, idDiscipline);
	}

}
