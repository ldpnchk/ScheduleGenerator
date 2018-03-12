package com.sg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "/worksheets", method = RequestMethod.GET)
	public String main(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
		
		model.addAttribute("worksheets", worksheetService.getByUser(userId));
		return "index";
	}
	
	@RequestMapping(value = "/settings/{wsId}", method = RequestMethod.GET)
	public String settings(Model model, @PathVariable("wsId") int wsId) {
		model.addAttribute("wsId", wsId);
		return "settings";
	}
	
	@RequestMapping(value = "/disciplines/{wsId}", method = RequestMethod.GET)
	public String disciplines(Model model, @PathVariable("wsId") int wsId) {
		model.addAttribute("disciplines", disciplineService.getAllByWorksheet(wsId));
		model.addAttribute("allStudents", studentService.getAllByWorksheet(wsId));
		model.addAttribute("allLecturers", lecturerService.getAllByWorksheet(wsId));
		model.addAttribute("specs", specialtyService.getAllByWorksheet(wsId));
		model.addAttribute("wsId", wsId);
		return "disciplines";
	}
	
	@RequestMapping(value = "/students/{wsId}", method = RequestMethod.GET)
	public String students(Model model, @PathVariable("wsId") int wsId) {
		model.addAttribute("students", studentService.getAllByWorksheet(wsId));
		model.addAttribute("disciplines", disciplineService.getAllByWorksheet(wsId));
		model.addAttribute("specs", specialtyService.getAllByWorksheet(wsId));
		model.addAttribute("wsId", wsId);
		return "students";
	}
	
	@RequestMapping(value = "/lecturers/{wsId}", method = RequestMethod.GET)
	public String lecturers(Model model, @PathVariable("wsId") int wsId) {
		model.addAttribute("lecturers", lecturerService.getAllByWorksheet(wsId));
		model.addAttribute("disciplines", disciplineService.getAllByWorksheet(wsId));
		model.addAttribute("specs", specialtyService.getAllByWorksheet(wsId));
		model.addAttribute("wsId", wsId);
		return "lecturers";
	}
	
}
