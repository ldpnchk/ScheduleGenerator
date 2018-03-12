package com.sg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sg.entity.*;
import com.sg.service.*;

@Controller
@RequestMapping("/restriction/")
public class RestrictionController {
	
	@Autowired
	private RestrictionService restrictionService;
	
	@Autowired
	private DisciplineService disciplineService;
	
	@Autowired
	private LecturerService lecturerService;
	
	@Autowired
	private ClassroomService classroomService;
	
	@Autowired
	private DaytimeService daytimeService;
	
	@Autowired
	private PeriodtimeService periodtimeService;
	
	@ResponseBody
	@RequestMapping(value = "/create/{wsId}", method = RequestMethod.POST)
    public String createLesson(@RequestParam int disc_id, @RequestParam int lect_id, @RequestParam int class_id, @RequestParam int day_id, @RequestParam int period_id, @RequestParam int selection, @PathVariable int wsId){
		Restriction restriction = new Restriction();
		if (disc_id != 0){
			restriction.setDiscipline(new Discipline(disc_id));
		}
		if (lect_id != 0){
			restriction.setLecturer(new Lecturer(lect_id));
		}
		if (class_id != 0){
			restriction.setClassroom(new Classroom(class_id));
		}
		if (day_id != 0){
			restriction.setDaytime(new Daytime(day_id));
		}
		if (period_id != 0){
			restriction.setPeriodtime(new Periodtime(period_id));
		}
		if (selection == 1){
			restriction.setSelection(true);
		}
		else{
			restriction.setSelection(false);
		}
		restriction.setWorksheetId(wsId);
		restrictionService.add(restriction);
    	return "success";
    }
	
	@ResponseBody
	@RequestMapping(value = "/delete/", method = RequestMethod.POST)
    public String deleteLesson(@RequestParam int id){
		restrictionService.delete(id);
        return "success";
    }
	
    @RequestMapping(value = "/view/{wsId}", method = RequestMethod.GET)
    public String viewRestriction(Model model, @PathVariable int wsId){
    	model.addAttribute("restrictions", restrictionService.getAllByWorksheet(wsId));
    	model.addAttribute("disciplines", disciplineService.getAllByWorksheet(wsId));
    	model.addAttribute("lecturers", lecturerService.getAllByWorksheet(wsId));
    	model.addAttribute("classrooms", classroomService.getAllWithToolsByWorksheet(wsId));
    	model.addAttribute("daytimes", daytimeService.getAll());
    	model.addAttribute("periodtimes", periodtimeService.getAll());
    	model.addAttribute("wsId", wsId);
        return "restriction";
    }
    
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteRestriction(@PathVariable int id){
    	restrictionService.delete(id);
        return "redirect:/restriction/view/";
    }

}
