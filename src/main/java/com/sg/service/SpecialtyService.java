package com.sg.service;

import java.util.List;

import com.sg.entity.Specialty;

public interface SpecialtyService {
	
	void addNewSpecialty(Specialty s);
	Specialty getById(int idSpecialty);
	List<Specialty> getAllByWorksheet(int id_worksheet);

}
