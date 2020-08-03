package com.spring.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.model.Instructor;
import com.spring.rest.repository.InstructorRepository;

@RestController
public class InstructorContoller {
	
	@Autowired
	private InstructorRepository instructorrepository;
	
	@PostMapping("/instructor")
	public Instructor postCourse(@RequestBody Instructor instructor){
		return instructorrepository.save(instructor);
	}
	
	// fetch all instructor who are teaching course with name as : Spring API
	@GetMapping("/instructor/course/{courseName}")
	public List<Instructor> getInstructorByCourseName(@PathVariable("courseName") String cname){
		// go to instrutorRepo & write native or JPQL for this operation
		//Native Query Call
		//String name = instructorrepository.getInstructorByCourseName(cname);
		//JPQL
		List<Instructor> list = instructorrepository.getInstructorByCourseNameJPQL(cname);
		return list;
	}
	
}
