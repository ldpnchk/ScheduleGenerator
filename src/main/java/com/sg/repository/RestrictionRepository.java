package com.sg.repository;

import java.util.List;

import com.sg.entity.Restriction;

public interface RestrictionRepository {
	
	void add(Restriction restriction);
	void delete(int idRestriction);
	
	List<Restriction> getAllByWorksheet(int id_worksheet);

}
