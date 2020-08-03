package com.spring.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest.model.Course;

//we will access database table through JpaRepository and not by firing query
//make interface and extend it so that we wont have to over-ride all method
//Long is nothing but the data-type of the ID of course primary key
public interface CourseRepository extends JpaRepository<Course, Long>{
	
	// Based on Course price fetch all courses
	List<Course> findAllByPrice(double price);
	
	// Fetch all courses based on Instructor name
	List<Course> findAllByInstructorName(String name);
}
