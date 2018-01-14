package com.sg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sg.service.DisciplineService;
import com.sg.service.LecturerService;
import com.sg.service.SpecialtyService;
import com.sg.service.StudentService;
import com.sg.service.WorksheetService;

@Controller
public class MainController {
	
	@Autowired
	private WorksheetService worksheetService;
	
	@Autowired
	private DisciplineService disciplineService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SpecialtyService specialtyService;
	
	@Autowired
	private LecturerService lecturerService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) {
		model.addAttribute("worksheets", worksheetService.getAll());
		return "index";
	}
	
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String settings() {
		return "settings";
	}
	
	@RequestMapping(value = "/disciplines", method = RequestMethod.GET)
	public String disciplines(Model model) {
		model.addAttribute("disciplines", disciplineService.getAll());
		model.addAttribute("allStudents", studentService.getAll());
		model.addAttribute("allLecturers", lecturerService.getAll());
		model.addAttribute("specs", specialtyService.getAll());
		return "disciplines";
	}
	
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public String students(Model model) {
		model.addAttribute("students", studentService.getAll());
		model.addAttribute("disciplines", disciplineService.getAll());
		model.addAttribute("specs", specialtyService.getAll());
		return "students";
	}
	
	@RequestMapping(value = "/lecturers", method = RequestMethod.GET)
	public String lecturers(Model model) {
		model.addAttribute("lecturers", lecturerService.getAll());
		model.addAttribute("disciplines", disciplineService.getAll());
		model.addAttribute("specs", specialtyService.getAll());
		return "lecturers";
	}
	
}
