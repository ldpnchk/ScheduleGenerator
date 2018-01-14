package com.sg.service;

import java.util.List;

import com.sg.entity.Restriction;

public interface RestrictionService {
	
	void add(Restriction restriction);
	void delete(int idRestriction);
	
	List<Restriction> getAll();

}
