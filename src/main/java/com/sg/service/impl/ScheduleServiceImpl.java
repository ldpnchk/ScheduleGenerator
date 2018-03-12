package com.sg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.alg_csp.Condition;
import com.sg.alg_csp.Generator;
import com.sg.entity.*;
import com.sg.repository.ScheduleRepository;
import com.sg.service.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ClassroomService classroomService;
	
	@Autowired
	private DaytimeService daytimeService;
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private PeriodtimeService periodtimeService;
	
	@Autowired
	private RestrictionService restrictionService;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Override
	public void generate(int idWorksheet) {
		List<Lesson> lessons = lessonService.getAllByWorksheet(idWorksheet);
		List<Classroom> classrooms = classroomService.getAllWithToolsByWorksheet(idWorksheet);
		List<Daytime> daytimes = daytimeService.getAll();
		List<Periodtime> periodtimes = periodtimeService.getAll();
		List<Restriction> restrictions = restrictionService.getAllByWorksheet(idWorksheet);

		deleteAllByWorksheet(idWorksheet);
		
		Generator generator = new Generator(lessons, classrooms, daytimes, periodtimes, restrictions);
		
		HashMap<Lesson, Condition> result = generator.getResult().get(0);
		Set<Lesson> ls = result.keySet();
		for (Lesson l : ls){
			scheduleRepository.add(new Schedule(l, result.get(l).getClassroom(), result.get(l).getDay(), result.get(l).getPeriod()));
		}
		
	}

	@Override
	public void deleteAllByWorksheet(int idWorksheet) {
		scheduleRepository.deleteAllByWorksheet(idWorksheet);
	}

	@Override
	public List<Schedule> getAllByWorksheet(int idWorksheet) {
		return scheduleRepository.getAllByWorksheet(idWorksheet);
	}

	@Override
	public List<Schedule> getAllByCourseSpecialtyWorksheet(int course, int idSpecialty, int idWorksheet) {
		return scheduleRepository.getAllByCourseSpecialtyWorksheet(course, idSpecialty, idWorksheet);
	}

}
