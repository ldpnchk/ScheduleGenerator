package com.sg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.entity.Specialty;
import com.sg.repository.SpecialtyRepository;
import com.sg.service.SpecialtyService;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
	
	@Autowired
	private SpecialtyRepository specialtyRepository;
	
	@Override
	public void addNewSpecialty(Specialty s) {
		specialtyRepository.addNewSpecialty(s);
	}
	
	@Override
	public List<Specialty> getAllByWorksheet(int id_worksheet) {
		return specialtyRepository.getAllByWorksheet(id_worksheet);
	}

	@Override
	public Specialty getById(int idSpecialty) {
		return specialtyRepository.getById(idSpecialty);
	}

}