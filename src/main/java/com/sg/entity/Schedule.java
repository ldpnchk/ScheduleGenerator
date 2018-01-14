package com.sg.entity;

public class Schedule {
	
	private Lesson lesson;
	private Classroom classroom;
	private Daytime daytime;
	private Periodtime periodtime;
	
	public Schedule() {
		
	}

	public Schedule(Lesson lesson, Classroom classroom, Daytime daytime, Periodtime periodtime) {
		this.lesson = lesson;
		this.classroom = classroom;
		this.daytime = daytime;
		this.periodtime = periodtime;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classroom == null) ? 0 : classroom.hashCode());
		result = prime * result + ((daytime == null) ? 0 : daytime.hashCode());
		result = prime * result + ((lesson == null) ? 0 : lesson.hashCode());
		result = prime * result + ((periodtime == null) ? 0 : periodtime.hashCode());
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
		Schedule other = (Schedule) obj;
		if (classroom == null) {
			if (other.classroom != null)
				return false;
		} else if (!classroom.equals(other.classroom))
			return false;
		if (daytime == null) {
			if (other.daytime != null)
				return false;
		} else if (!daytime.equals(other.daytime))
			return false;
		if (lesson == null) {
			if (other.lesson != null)
				return false;
		} else if (!lesson.equals(other.lesson))
			return false;
		if (periodtime == null) {
			if (other.periodtime != null)
				return false;
		} else if (!periodtime.equals(other.periodtime))
			return false;
		return true;
	}

}
