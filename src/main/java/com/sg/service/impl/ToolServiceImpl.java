package com.sg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.entity.Tool;
import com.sg.repository.ToolRepository;
import com.sg.service.ToolService;

@Service
public class ToolServiceImpl implements ToolService{
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Override
	public List<Tool> getAll() {
		return toolRepository.getAll();
	}

}
