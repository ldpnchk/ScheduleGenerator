package com.sg.entity;

public class User {

	private int id;
	private String name;
	private String password;
	int role;
		
	public User(int id, String name, String password, int role) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
	}
	
	public User(String name, String password, int role) {
		this.name = name;
		this.password = password;
		this.role = role;
	}
	
	public User(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public User() {
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    public int getRole() {
        return role;
    }
    public void setRole(int role) {
        this.role = role;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", role=" + role + "]";
	}
}