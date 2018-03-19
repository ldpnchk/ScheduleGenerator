package com.sg.repository;

import java.util.List;

import com.sg.entity.Specialty;

public interface SpecialtyRepository {
	
	Specialty getById(int idSpecialty);
	List<Specialty> getAllByWorksheet(int id_worksheet);
	void addNewSpecialty(Specialty s);

}
