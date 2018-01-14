package com.sg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.entity.Periodtime;
import com.sg.repository.PeriodtimeRepository;
import com.sg.service.PeriodtimeService;

@Service
public class PeriodtimeServiceImpl implements PeriodtimeService {
	
	@Autowired
	private PeriodtimeRepository periodtimeRepository;

	@Override
	public List<Periodtime> getAll() {
		return periodtimeRepository.getAll();
	}

}