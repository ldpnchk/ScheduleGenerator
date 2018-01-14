package com.sg.service;

import java.util.List;

import com.sg.entity.Specialty;

public interface SpecialtyService {
	
	Specialty getById(int idSpecialty);
	List<Specialty> getAll();

}
