package com.sg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.entity.Lesson;
import com.sg.repository.LessonRepository;
import com.sg.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {
	
	@Autowired
	private LessonRepository lessonRepository;

	@Override
	public void add(Lesson lesson) {
		lessonRepository.add(lesson);
	}

	@Override
	public void delete(int idLesson) {
		lessonRepository.delete(idLesson);
	}
	
	@Override
	public void addStudent(int idLesson, int idStudent) {
		lessonRepository.addStudent(idLesson, idStudent);
	}
	
	@Override
	public void addTool(int idLesson, int idTool, int selection) {
		lessonRepository.addTool(idLesson, idTool, selection);
	}

	@Override
	public Lesson getById(int idLesson) {
		return lessonRepository.getById(idLesson);
	}

	@Override
	public Map<Lesson, Integer> getAllWithStudentsAmountByWorksheet(int idLesson) {
		return lessonRepository.getAllWithStudentsAmountByWorksheet(idLesson);
	}

	@Override
	public List<Lesson> getAllByWorksheet(int idWorksheet) {
		return lessonRepository.getAllByWorksheet(idWorksheet);
	}
	
	
	
}
