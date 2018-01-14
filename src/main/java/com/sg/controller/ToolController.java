package com.sg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sg.entity.Tool;
import com.sg.service.ToolService;

@Controller
@RequestMapping("/tool/")
public class ToolController {
	
	@Autowired
	private ToolService toolService;
	
	@ResponseBody
	@RequestMapping(value = "/getAll", method = RequestMethod.POST, produces = "application/json")
	public List<Tool> getTools() {
		List<Tool> tools = toolService.getAll();
		return tools;
	}

}
