package com.sg.entity;

public class Restriction {
	
	private int id;
	private Discipline discipline;
	private Lecturer lecturer;
	private Classroom classroom;
	private Daytime daytime;
	private Periodtime periodtime;
	private boolean selection;
	private int worksheet_id;
	
	public Restriction() {

	}

	public Restriction(int id) {
		this.id = id;
	}

	public Restriction(int id, Discipline discipline, Lecturer lecturer, Classroom classroom, Daytime daytime,
			Periodtime periodtime, boolean selection) {
		this.id = id;
		this.discipline = discipline;
		this.lecturer = lecturer;
		this.classroom = classroom;
		this.daytime = daytime;
		this.periodtime = periodtime;
		this.selection = selection;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public Daytime getDaytime() {
		return daytime;
	}

	public void setDaytime(Daytime daytime) {
		this.daytime = daytime;
	}

	public Periodtime getPeriodtime() {
		return periodtime;
	}

	public void setPeriodtime(Periodtime periodtime) {
		this.periodtime = periodtime;
	}

	public boolean isSelection() {
		return selection;
	}

	public void setSelection(boolean selection) {
		this.selection = selection;
	}
	
	public int getWorksheetId() {
		return worksheet_id;
	}
	
	public void setWorksheetId(int worksheet_id) {
		this.worksheet_id = worksheet_id;
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
		Restriction other = (Restriction) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
