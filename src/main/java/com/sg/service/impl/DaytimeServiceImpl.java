package com.sg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.entity.Daytime;
import com.sg.repository.DaytimeRepository;
import com.sg.service.DaytimeService;

@Service
public class DaytimeServiceImpl implements DaytimeService {
	
	@Autowired
	private DaytimeRepository daytimeRepository;

	@Override
	public List<Daytime> getAll() {
		return daytimeRepository.getAll();
	}

}