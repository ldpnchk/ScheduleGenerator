package com.sg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.entity.RoomType;
import com.sg.repository.RoomTypeRepository;
import com.sg.service.RoomTypeService;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

	@Autowired
	private RoomTypeRepository roomTypeRepository;
	
	@Override
	public List<RoomType> getAll() {
		return roomTypeRepository.getAll();
	}

}
