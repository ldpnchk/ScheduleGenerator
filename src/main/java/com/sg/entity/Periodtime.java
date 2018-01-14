package com.sg.entity;

public class Periodtime {

	private int id;
	private int number;
	
	public Periodtime() {
		
	}
	
	public Periodtime(int id) {
		this.id = id;
	}
	
	public Periodtime(int id, int number) {
		this.id = id;
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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
		Periodtime other = (Periodtime) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Periodtime [id=" + id + ", name=" + number + "]";
	}
	
}
