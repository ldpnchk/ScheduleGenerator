package com.sg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.entity.Worksheet;
import com.sg.repository.WorksheetRepository;
import com.sg.service.WorksheetService;

@Service
public class WorksheetServiceImpl implements WorksheetService {
	
	@Autowired
	private WorksheetRepository worksheetRepository;

	@Override
	public void add(Worksheet worksheet) {
		worksheetRepository.add(worksheet);
	}

	@Override
	public void update(Worksheet worksheet) {
		worksheetRepository.update(worksheet);
	}

	@Override
	public void delete(int idWorksheet) {
		worksheetRepository.delete(idWorksheet);
	}
	
	@Override
	public Worksheet getById(int idWorksheet) {
		return worksheetRepository.getById(idWorksheet);
	}

	@Override
	public List<Worksheet> getByUser(int userId) {
		return worksheetRepository.getByUser(userId);
	}

}
