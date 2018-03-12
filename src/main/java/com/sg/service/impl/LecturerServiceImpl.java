package com.sg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.entity.Lecturer;
import com.sg.repository.LecturerRepository;
import com.sg.service.LecturerService;

@Service
public class LecturerServiceImpl implements LecturerService {

	@Autowired
	private LecturerRepository lecturerRepository;
	
	@Override
	public void add(Lecturer lecturer) {
		lecturerRepository.add(lecturer);
	}

	@Override
	public void update(Lecturer lecturer) {
		lecturerRepository.update(lecturer);
	}

	@Override
	public void delete(int idLecturer) {
		lecturerRepository.delete(idLecturer);
	}

	@Override
	public List<Lecturer> getAllByWorksheet(int id_worksheet) {
		return lecturerRepository.getAllByWorksheet(id_worksheet);
	}

	@Override
	public List<Lecturer> getAllByDiscipline(int idDiscipline) {
		return lecturerRepository.getAllByDiscipline(idDiscipline);
	}

}
