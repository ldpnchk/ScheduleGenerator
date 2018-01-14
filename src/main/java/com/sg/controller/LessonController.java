package com.sg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sg.entity.*;
import com.sg.service.LessonService;

@Controller
@RequestMapping("/lesson/")
public class LessonController {
	
	@Autowired
	private LessonService lessonService;
	
	@ResponseBody
	@RequestMapping(value = "/create/", method = RequestMethod.POST)
    public String createLesson(@RequestParam int id, @RequestParam String name, @RequestParam int disp_id, @RequestParam int lect_id, @RequestParam int room_type_id,  @RequestParam(value = "students[]") Integer[] students, @RequestParam(value = "toolsy[]") Integer[] toolsy, @RequestParam(value = "toolsn[]") Integer[] toolsn){
		Lesson lesson = new Lesson();
		lesson.setWorksheet(new Worksheet(id));
		lesson.setName(name);
		lesson.setDiscipline(new Discipline(disp_id));
		lesson.setLecturer(new Lecturer(lect_id));
		lesson.setRoomType(new RoomType(room_type_id));
    	lessonService.add(lesson);
    	for (Integer i : students){
    		if (i != 0){
    			lessonService.addStudent(lesson.getId(), i);
    		}
    	}

    	for (Integer i : toolsy){
    		if (i != 0){
    			lessonService.addTool(lesson.getId(), i, 1);
    		}
    	}
    	for (Integer i : toolsn){
    		if (i != 0){
    			lessonService.addTool(lesson.getId(), i, 0);
    		}
    	}
    	
    	return "success";
    }
	
	@RequestMapping(value = "/delete/{wid}/{id}", method = RequestMethod.GET)
    public String deleteLesson(@PathVariable int id, @PathVariable int wid){
    	lessonService.delete(id);
        return "redirect:/worksheet/view/" + wid + "/";
    }

}
