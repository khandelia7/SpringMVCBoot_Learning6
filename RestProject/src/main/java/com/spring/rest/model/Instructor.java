package com.spring.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //make this class as table in database
@Table(name = "instructor") //change table name 
public class Instructor {
	
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.AUTO) //auto increment
	private Long id;
	
	private String name;
	
	private Double salary;
	
//	One Instructor has Many Course [Instructor side] : 1:M 
//	One Course has many Instructor [Course Side] :  M to 1
//  Note In Instructor no need to mention course
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the salary
	 */
	public Double getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
	}

}
