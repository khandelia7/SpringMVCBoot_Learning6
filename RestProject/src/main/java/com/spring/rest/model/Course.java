package com.spring.rest.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity //make this class as table in database
@Table(name = "course") //change table name 
public class Course implements Comparable<Course>{

	@Id //primary key // Very Important
	@GeneratedValue(strategy = GenerationType.AUTO) //auto increment
	// Hibernate Sequence
	private Long id; //Always keep a class and not primitive

	@Column(name = "course_name") //change column name
	private String name;
	
	private Double price;
	
	private String shortDescription;

	@Column(length=10000)
	private String description;
	
//	One Instructor has Many Course [Instructor side] : 1:M 
//	One Course has many Instructor [Course Side] :  M to 1
	@ManyToOne
	private Instructor instructor;
	
	@ManyToMany(mappedBy = "courses")
	List<Student> students;
	
	//IMPORTANT #StudentController
	@Override
	public int compareTo(Course c) {
		boolean status = this.getId().equals(c.getId());
		return status? 0: 1;
	}

	/**
	 * @return the instructor
	 */
	public Instructor getInstructor() {
		return instructor;
	}

	/**
	 * @param instructor the instructor to set
	 */
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

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
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * @param shortDescription the shortDescription to set
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the students
	 */
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * @param students the students to set
	 */
	public void setStudents(List<Student> students) {
		this.students = students;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
}
