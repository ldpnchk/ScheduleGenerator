package com.sg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sg.entity.RoomType;
import com.sg.service.RoomTypeService;

@Controller
@RequestMapping("/roomType/")
public class RoomTypeController {
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	@ResponseBody
	@RequestMapping(value = "/getAll", method = RequestMethod.POST, produces = "application/json")
	public List<RoomType> getRoomTypes() {
		List<RoomType> roomTypes = roomTypeService.getAll();
		return roomTypes;
	}

}
