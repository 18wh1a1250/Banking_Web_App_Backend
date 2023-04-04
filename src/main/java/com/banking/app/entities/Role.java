package com.banking.app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//import jakarta.persistence.Id;
//import jakarta.persistence.Table;

@Table
@Entity
public class Role {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	public Role(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}


	private String name;


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
	
}
