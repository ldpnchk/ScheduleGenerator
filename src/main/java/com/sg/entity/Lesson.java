package com.sg.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Lesson {
	
	private int id;
	private String name;
	private Worksheet worksheet;
	private Lecturer lecturer;
	private Discipline discipline;
	private RoomType roomType;
	private ArrayList<Student> students = new ArrayList<Student>();
	private HashMap<Tool, Boolean> tools = new HashMap<Tool, Boolean>();
	
	public Lesson() {
		
	}

	public Lesson(int id) {
		this.id = id;
	}
	
	public Lesson(int id, String name, Worksheet worksheet, Lecturer lecturer, Discipline discipline, RoomType roomType) {
		this.id = id;
		this.name = name;
		this.worksheet = worksheet;
		this.lecturer = lecturer;
		this.discipline = discipline;
		this.roomType = roomType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Worksheet getWorksheet() {
		return worksheet;
	}

	public void setWorksheet(Worksheet worksheet) {
		this.worksheet = worksheet;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public HashMap<Tool, Boolean> getTools() {
		return tools;
	}

	public void setTools(HashMap<Tool, Boolean> tools) {
		this.tools = tools;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
