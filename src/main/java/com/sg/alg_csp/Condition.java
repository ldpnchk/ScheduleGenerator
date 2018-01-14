package com.sg.alg_csp;


import com.sg.entity.Classroom;
import com.sg.entity.Daytime;
import com.sg.entity.Periodtime;

public class Condition {
	
	private Daytime day;
	private Periodtime period;
	private Classroom classroom;
	
	public Condition(Daytime day, Periodtime period, Classroom classroom) {
		this.day = day;
		this.period = period;
		this.classroom = classroom;
	}

	public Daytime getDay() {
		return day;
	}

	public void setDay(Daytime day) {
		this.day = day;
	}

	public Periodtime getPeriod() {
		return period;
	}

	public void setPeriod(Periodtime period) {
		this.period = period;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classroom == null) ? 0 : classroom.hashCode());
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((period == null) ? 0 : period.hashCode());
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
		Condition other = (Condition) obj;
		if (classroom == null) {
			if (other.classroom != null)
				return false;
		} else if (!classroom.equals(other.classroom))
			return false;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (period == null) {
			if (other.period != null)
				return false;
		} else if (!period.equals(other.period))
			return false;
		return true;
	}

}
