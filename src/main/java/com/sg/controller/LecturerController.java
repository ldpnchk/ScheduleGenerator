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

import com.sg.entity.Lecturer;
import com.sg.service.LecturerService;

@Controller
@RequestMapping("/lecturer/")
public class LecturerController {
	
	@Autowired
	private LecturerService lecturerService;
	
	@ResponseBody
	@RequestMapping(value = "/getAllByDiscipline", method = RequestMethod.POST, produces = "application/json")
	public List<Lecturer> getLecturers(@RequestParam int id) {
		List<Lecturer> lecturers = lecturerService.getAllByDiscipline(id);
		return lecturers;
	}
	
	@ResponseBody
	@RequestMapping(value = "/create/", method = RequestMethod.POST)
    public String createDiscipline(@RequestParam String name){
    	Lecturer lecturer = new Lecturer();
    	lecturer.setName(name);
    	lecturerService.add(lecturer);
    	return "success";
    }
	
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteWorksheet(@PathVariable int id, Model model){
    	lecturerService.delete(id);
    	model.addAttribute("lecturers", lecturerService.getAll());
        return "lecturers";
    }
	
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewDiscipline(@PathVariable int id, Model model){
    	List<Lecturer> lecturers = lecturerService.getAll();
    	Lecturer lecturer = null;
    	for(Lecturer temp : lecturers){
    		if(temp.getId()==id){
    			lecturer = temp;
    		}
    	}
    	model.addAttribute("lecturer", lecturer);
        return "lecturerDetails";
    }
    
}
