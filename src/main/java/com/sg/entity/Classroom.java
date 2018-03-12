package com.sg.entity;

import java.util.ArrayList;

public class Classroom {
	
	private int id;
	private String building;
	private String number;
	private int capacity;
	private RoomType roomType;
	private ArrayList<Tool> tools = new ArrayList<Tool>();
	private int worksheet_id;
	
	public Classroom() {
		
	}
	
	public Classroom(int id) {
		this.id = id;
	}

	public Classroom(int id, String building, String number, int capacity, RoomType roomType) {
		this.id = id;
		this.building = building;
		this.number = number;
		this.capacity = capacity;
		this.roomType = roomType;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getBuilding() {
		return building;
	}
	
	public void setBuilding(String building) {
		this.building = building;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public ArrayList<Tool> getTools() {
		return tools;
	}

	public void setTools(ArrayList<Tool> tools) {
		this.tools = tools;
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
		Classroom other = (Classroom) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
