package com.sg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.entity.Classroom;
import com.sg.repository.ClassroomRepository;
import com.sg.service.ClassroomService;

@Service
public class ClassroomServiceImpl implements ClassroomService {
	
	@Autowired
	private ClassroomRepository classroomRepository;

	@Override
	public List<Classroom> getAllWithTools() {
		return classroomRepository.getAllWithTools();
	}

}