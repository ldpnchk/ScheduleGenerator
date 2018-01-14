package com.sg.repository;

import java.util.List;
import java.util.Map;

import com.sg.entity.Lesson;

public interface LessonRepository {
	
	void add(Lesson lesson);
	void delete(int idLesson);
	
	void addStudent(int idLesson, int idStudent);
	void addTool(int idLesson, int idTool, int selection);
	
	Lesson getById(int idLesson);
	Map<Lesson, Integer> getAllWithStudentsAmountByWorksheet(int idWorksheet);
	List<Lesson> getAllByWorksheet(int idWorksheet);

}
