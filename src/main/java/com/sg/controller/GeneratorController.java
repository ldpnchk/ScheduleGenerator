package com.sg.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sg.alg_csp.Condition;
import com.sg.docx.CreateDocx;
import com.sg.entity.Lesson;
import com.sg.entity.Schedule;
import com.sg.entity.extra.LessonTotal;
import com.sg.service.LessonService;
import com.sg.service.ScheduleService;
import com.sg.service.SpecialtyService;

@Controller
@RequestMapping("/generator/")
public class GeneratorController {
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private SpecialtyService specialtyService;
	
	@ResponseBody
	@RequestMapping(value = "/generate/", method = RequestMethod.POST)
    public String generateAnswer(@RequestParam int id){
		scheduleService.generate(id);
        return "success";
    }

	@RequestMapping(value = "/get/{year}/{specialtyId}/{idWorksheet}", method = RequestMethod.GET)
	public void download(@PathVariable int year, @PathVariable int specialtyId,@PathVariable int idWorksheet, HttpServletResponse response) throws IOException {
		Map<Lesson, Integer> lessons = lessonService.getAllWithStudentsAmountByWorksheet(idWorksheet);
		List<Schedule> schedules = scheduleService.getAllByCourseSpecialtyWorksheet(year, specialtyId, idWorksheet);
    	List<LessonTotal> result = new ArrayList<LessonTotal>();
    	for (Lesson l : lessons.keySet()){
    		result.add(new LessonTotal(l, lessons.get(l), findCondition(l.getId(), schedules)));
    	};
    	///
    	String specialty = specialtyService.getById(specialtyId).getName();
    	//
    	try{
    	File file = CreateDocx.create(result, year, specialty);
    	InputStream is = new FileInputStream(file);
		
		// MIME type of the file
		response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		
		// Response header
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ file.getName() + "\"");
		
		// Read from the file and write into the response
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = is.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		os.flush();
		os.close();
		is.close();
    	}catch(NullPointerException ex){
    		// LOL
    	}
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
