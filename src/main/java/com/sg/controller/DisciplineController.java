package com.sg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sg.entity.Discipline;
import com.sg.entity.Lecturer;
import com.sg.entity.Student;
import com.sg.entity.Worksheet;
import com.sg.service.DisciplineService;
import com.sg.service.LecturerService;
import com.sg.service.StudentService;

@Controller
@RequestMapping("/discipline/")
public class DisciplineController {
	
	@Autowired
	private DisciplineService disciplineService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private LecturerService lecturerService;
	
	@ResponseBody
	@RequestMapping(value = "/getAllByLecturer/{id}", method = RequestMethod.POST, produces = "application/json")
	public List<Discipline> getAllByLecturer(@PathVariable int id) {
		List<Discipline> disciplines = disciplineService.getAllByLecturer(id);
		return disciplines;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAllByStudent/{id}", method = RequestMethod.POST, produces = "application/json")
	public List<Discipline> getAllByStudent(@PathVariable int id) {
		List<Discipline> disciplines = disciplineService.getAllByStudent(id);
		return disciplines;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAll/{worksheet_id}", method = RequestMethod.POST, produces = "application/json")
	public List<Discipline> getDisciplines(@PathVariable int worksheet_id) {
		List<Discipline> disciplines = disciplineService.getAllByWorksheet(worksheet_id);
		return disciplines;
	}
	
	@ResponseBody
	@RequestMapping(value = "/create/{worksheet_id}", method = RequestMethod.POST)
    public String createDiscipline(@RequestParam String name, @PathVariable int worksheet_id){
    	Discipline discipline = new Discipline();
    	discipline.setName(name);
    	discipline.setWorksheetId(worksheet_id);
    	disciplineService.add(discipline);
    	return "success";
    }
	
    @RequestMapping(value = "/delete/{id}/{wsId}", method = RequestMethod.GET)
    public String deleteDiscipline(@PathVariable int id, @PathVariable int wsId, Model model){
    	disciplineService.delete(id);
    	model.addAttribute("disciplines", disciplineService.getAllByWorksheet(wsId));
        return "disciplines";
    }
    
    @ResponseBody
    @RequestMapping(value = "/viewL/{id}", method = RequestMethod.POST, produces = "application/json")
    public List<Lecturer> viewDisciplineLecturers(@PathVariable int id, Model model){
    	List<Lecturer> lecturers = lecturerService.getAllByDiscipline(id);
        return lecturers;
    }
    
    @ResponseBody
    @RequestMapping(value = "/viewS/{id}", method = RequestMethod.POST, produces = "application/json")
    public List<Student> viewDisciplineStudents(@PathVariable int id, Model model){
    	List<Student> students = studentService.getAllByDiscipline(id);
    	return students;
    }
    
    @ResponseBody
    @RequestMapping(value = "/addStudent/{idDiscipline}/{idStudent}", method = RequestMethod.GET)
    public String addStudent(@PathVariable int idDiscipline, @PathVariable int idStudent){
    	disciplineService.addStudent(idDiscipline, idStudent);
    	return "success";
    }
    
    @ResponseBody
    @RequestMapping(value = "/removeStudent/{idDiscipline}/{idStudent}", method = RequestMethod.GET)
    public String removeStudent(@PathVariable int idDiscipline, @PathVariable int idStudent){
    	disciplineService.removeStudent(idDiscipline, idStudent);
    	return "success";
    }
    
    @ResponseBody
    @RequestMapping(value = "/addLecturer/{idDiscipline}/{idLecturer}", method = RequestMethod.GET)
    public String addLecturer(@PathVariable int idDiscipline, @PathVariable int idLecturer){
    	disciplineService.addLecturer(idDiscipline, idLecturer);
    	return "success";
    }
    
    @ResponseBody
    @RequestMapping(value = "/removeLecturer/{idDiscipline}/{idLecturer}", method = RequestMethod.GET)
    public String removeLecturer(@PathVariable int idDiscipline, @PathVariable int idLecturer){
    	disciplineService.removeLecturer(idDiscipline, idLecturer);
    	return "success";
    }
    
}