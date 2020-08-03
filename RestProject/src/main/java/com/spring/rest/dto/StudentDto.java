package com.spring.rest.dto;

import java.util.List;

public class StudentDto {
	private Long id;
	private String studentName;
	private List<String> courseName;

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
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * @return the courseName
	 */
	public List<String> getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(List<String> courseName) {
		this.courseName = courseName;
	}
	/**
	 * @return the instructorName
	 */
}
