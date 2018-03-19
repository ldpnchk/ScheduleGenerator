package com.sg.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sg.alg_csp.Condition;
import com.sg.entity.Classroom;
import com.sg.entity.Lesson;
import com.sg.entity.RoomType;
import com.sg.entity.Schedule;
import com.sg.entity.Specialty;
import com.sg.entity.Tool;
import com.sg.entity.Worksheet;
import com.sg.entity.extra.LessonTotal;
import com.sg.service.ClassroomService;
import com.sg.service.LessonService;
import com.sg.service.ScheduleService;
import com.sg.service.SpecialtyService;
import com.sg.service.ToolService;
import com.sg.service.WorksheetService;

@Controller
@RequestMapping("/worksheet/")
public class WorksheetController {

	@Autowired
	private WorksheetService worksheetService;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private LessonService lessonService;

	@Autowired
	private SpecialtyService specialtyService;

	@Autowired
	private ToolService toolService;

	@Autowired
	private ClassroomService classroomService;

	@ResponseBody
	@RequestMapping(value = "/create/", method = RequestMethod.POST)
	public int createWorksheet(@RequestParam String name, HttpServletRequest request){
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
		Worksheet worksheet = new Worksheet();
		worksheet.setName(name);
		worksheet.setUserId(userId);
		worksheetService.add(worksheet);
		return worksheet.getId();
	}

	@ResponseBody
	@RequestMapping(value = "/update/", method = RequestMethod.POST)
	public String updateWorkcheet(@RequestParam int id, @RequestParam String name, HttpServletRequest request){
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
		Worksheet worksheet = new Worksheet(id, name, userId);
		worksheetService.update(worksheet);
		return "success";
	}

	@RequestMapping(value = "/delete/", method = RequestMethod.POST)
	public String deleteWorksheet(@RequestParam int id){
		worksheetService.delete(id);
		return "redirect:/";
	}

	@RequestMapping(value = "/view/{id}/", method = RequestMethod.GET)
	public String viewTeam(@PathVariable int id, Model model){
		Worksheet worksheet = worksheetService.getById(id);
		model.addAttribute("worksheet", worksheet);
		Map<Lesson, Integer> lessons = lessonService.getAllWithStudentsAmountByWorksheet(id);
		List<Schedule> schedules = scheduleService.getAllByWorksheet(id);
		List<LessonTotal> result = new ArrayList<LessonTotal>();
		for (Lesson l : lessons.keySet()){
			result.add(new LessonTotal(l, lessons.get(l), findCondition(l.getId(), schedules)));
		}
		System.out.println(result.size());
		System.out.println(specialtyService.getAllByWorksheet(id).size());
		model.addAttribute("result", result);
		model.addAttribute("specs", specialtyService.getAllByWorksheet(id));
		return "worksheet";
	}

	@ResponseBody
	@RequestMapping(value = "/createClassroom", method = RequestMethod.POST)
	public String createClassroom(@RequestParam String building, @RequestParam String numClassroom,
			@RequestParam int capacity,@RequestParam int type,@RequestParam int tool, @RequestParam int wsId){
		try{
			Classroom classroom = new Classroom();
			classroom.setBuilding(building);
			classroom.setCapacity(capacity);
			classroom.setNumber(numClassroom);
			classroom.setWorksheetId(wsId);
			if(tool==1){
				ArrayList<Tool> tools = new ArrayList<Tool>(toolService.getAll());
				classroom.setTools(tools);
			}else{
				ArrayList<Tool> tools = new ArrayList<Tool>();
				classroom.setTools(tools);
			}
			if(type==1){
				classroom.setRoomType(new RoomType(1, ""));
			}
			if(type==2){
				classroom.setRoomType(new RoomType(2, ""));
			}
			classroomService.addClassroom(classroom);
		}catch(Exception e){
			return "error";
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/createSpeciality", method = RequestMethod.POST)
	public String createSpeciality(@RequestParam String name, @RequestParam int wsId){
		try{
			Specialty spec = new Specialty();
			spec.setName(name);
			spec.setWorksheetId(wsId);
			specialtyService.addNewSpecialty(spec);
		}catch(Exception e){
			return "error";
		}
		return "success";
	}

	private Condition findCondition(int idLesson, List<Schedule> schedules){
		for (Iterator<Schedule> it = schedules.iterator(); it.hasNext(); ) {
			Schedule s = it.next();
			if (s.getLesson().getId() == idLesson)
				return new Condition(s.getDaytime(), s.getPeriodtime(), s.getClassroom());
		}
		return null;
	}

}
