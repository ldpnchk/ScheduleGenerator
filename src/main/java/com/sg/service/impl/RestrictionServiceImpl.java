package com.sg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.entity.Restriction;
import com.sg.repository.RestrictionRepository;
import com.sg.service.RestrictionService;

@Service
public class RestrictionServiceImpl implements RestrictionService {
	
	@Autowired
	private RestrictionRepository restrictionRepository;
	
	@Override
	public void add(Restriction restriction) {
		restrictionRepository.add(restriction);
	}

	@Override
	public void delete(int idRestriction) {
		restrictionRepository.delete(idRestriction);
	}

	@Override
	public List<Restriction> getAllByWorksheet(int id_worksheet) {
		return restrictionRepository.getAllByWorksheet(id_worksheet);
	}

}