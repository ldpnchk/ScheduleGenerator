package com.sg.service;

import java.util.List;

import com.sg.entity.Worksheet;

public interface WorksheetService {
	
	void add(Worksheet worksheet);
	void update(Worksheet worksheet);
	void delete(int idWorksheet);
	
	Worksheet getById(int idWorksheet);
	List<Worksheet> getByUser(int userId);

}
