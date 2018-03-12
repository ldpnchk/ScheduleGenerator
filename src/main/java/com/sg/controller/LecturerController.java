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
	@RequestMapping(value = "/create/{wsId}", method = RequestMethod.POST)
    public String createDiscipline(@RequestParam String name, @PathVariable int wsId){
    	Lecturer lecturer = new Lecturer();
    	lecturer.setName(name);
    	lecturer.setWorksheetId(wsId);
    	lecturerService.add(lecturer);
    	return "success";
    }
	
    @RequestMapping(value = "/delete/{id}/{wsId}", method = RequestMethod.GET)
    public String deleteWorksheet(@PathVariable int id, @PathVariable int wsId, Model model){
    	lecturerService.delete(id);
    	model.addAttribute("lecturers", lecturerService.getAllByWorksheet(wsId));
        return "lecturers";
    }
	
    @RequestMapping(value = "/view/{id}/{wsId}", method = RequestMethod.GET)
    public String viewDiscipline(@PathVariable int id, @PathVariable int wsId, Model model){
    	List<Lecturer> lecturers = lecturerService.getAllByWorksheet(wsId);
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
