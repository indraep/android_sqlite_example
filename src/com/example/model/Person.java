package com.example.model;

public class Person {
	int id;
	String name;
	
	public Person() {
		
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}
