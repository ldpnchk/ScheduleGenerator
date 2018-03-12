package com.sg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.entity.Discipline;
import com.sg.repository.DisciplineRepository;
import com.sg.service.DisciplineService;

@Service
public class DisciplineServiceImpl implements DisciplineService {
	
	@Autowired
	private DisciplineRepository disciplineRepository;

	@Override
	public void add(Discipline discipline) {
		disciplineRepository.add(discipline);
	}

	@Override
	public void update(Discipline discipline) {
		disciplineRepository.update(discipline);
	}

	@Override
	public void delete(int idDiscipline) {
		disciplineRepository.delete(idDiscipline);
	}

	@Override
	public void addLecturer(int idDiscipline, int idLecturer) {
		disciplineRepository.addLecturer(idDiscipline, idLecturer);
	}

	@Override
	public void addStudent(int idDiscipline, int idStudent) {
		disciplineRepository.addStudent(idDiscipline, idStudent);
	}
	
	@Override
	public void removeLecturer(int idDiscipline, int idLecturer) {
		disciplineRepository.removeLecturer(idDiscipline, idLecturer);
	}

	@Override
	public void removeStudent(int idDiscipline, int idStudent) {
		disciplineRepository.removeStudent(idDiscipline, idStudent);
	}
	
	@Override
	public List<Discipline> getAllByLecturer(int idLecturer) {
		return disciplineRepository.getAllByLecturer(idLecturer);
	}

	@Override
	public List<Discipline> getAllByStudent(int idStudent) {
		return disciplineRepository.getAllByStudent(idStudent);
	}
	
	@Override
	public List<Discipline> getAllByWorksheet(int id_worksheet) {
		return disciplineRepository.getAllByWorksheet(id_worksheet);
	}

}
