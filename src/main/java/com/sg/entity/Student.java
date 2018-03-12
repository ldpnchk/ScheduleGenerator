package com.sg.entity;

public class Student {
	
	private int id;
	private String name;
	private int year;
	private Specialty specialty;
	private int worksheet_id;
	
	public Student() {

	}
	
	public Student(int id) {
		this.id = id;
	}
	
	public Student(int id, String name, int year, Specialty specialty) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.specialty = specialty;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
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
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
