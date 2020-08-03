package com.spring.rest.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.dto.CourseDto;
import com.spring.rest.model.Course;
import com.spring.rest.model.Instructor;
import com.spring.rest.repository.CourseRepository;
import com.spring.rest.repository.InstructorRepository;

@RestController
public class MainController {

	//always Auto-wired and not make a object
	@Autowired
	private CourseRepository courseRepository; 
	
	@Autowired
	private InstructorRepository instructorrepository;
	
	
	//1st API of simple hello
	@ GetMapping("/hello")
	public String helloMethod() {
		// int i = 9/0;
		return "Hello World";
	}
	
	//2nd & 8th API of POST Mapping API in which we will add courses plus instructor-id is also updated
	//Very Important that in URL itself we are passing the ID
	@PostMapping("/add-course/{instructid}")
	public Course postCourse(@PathVariable("instructid") long instructid, @RequestBody Course course){
		Instructor instructor = instructorrepository.getOne(instructid);
		if(instructor == null) {
			throw new RuntimeException("ID does not exist");
		}
		course.setInstructor(instructor);
		return courseRepository.save(course);
	}
	
	//3rd API of GET Mapping API in which courses and discounted value is given
	@GetMapping("/get-course")
	public CourseDto getCourses() {
		CourseDto dto = new CourseDto();
		double total= 0;
		List<Course> list =  courseRepository.findAll();
		for(Course c : list){
			total = total + c.getPrice();
		}
		total = total - (total * 0.1); //10% discount
		dto.setDiscountPrice(total);
		dto.setList(list);
		return dto;
	}
	
	
	//4th API of DELETE API in which we will delete course
	@DeleteMapping("/delete-course/{id}") //passing arguments
	public void deleteCourse(@PathVariable("id") long id){
		courseRepository.deleteById(id);
	}
	
	
	//5th API of PUT API in which we will modify/edit course
	@PutMapping("/edit-course/{id}") //passing arguments
	public Course editCourse(@PathVariable("id") long id,@RequestBody Course courseUpdated) {
		//courseUpdated is body which has to be edited
		
		//courseDB is temporary object which will edit the things for u
		Course courseDB = courseRepository.getOne(id);
		
		courseDB.setName(courseUpdated.getName());
		courseDB.setShortDescription(courseUpdated.getShortDescription());
		courseDB.setDescription(courseUpdated.getDescription());
		//price cannot be updated
		
		return courseRepository.save(courseDB);
	}
	
	
	//6th API of GET API in which paging (optional too) will be perform
	@GetMapping("/paging-courses")
	public CourseDto getCoursesPaging(@RequestParam(value="page",required=false,defaultValue="0") Integer page, 
									@RequestParam(value="size",required=false,defaultValue="1000") Integer size){
		//we will take 2 parameter page and size with flag and defaultValue as parameter 
		//This two things will make paging optional
		//Problem of paging is re-solve
		
		//Always use in built function algorithm 
		Pageable pageable = PageRequest.of(page, size);
		List<Course> list =  courseRepository.findAll(pageable).getContent();
		
		CourseDto dto = new CourseDto();
		double total= 0;
		
		for(Course c : list){
			total = total + c.getPrice();
		}
		total = total - (total * 0.1); //10% discount
		
		dto.setDiscountPrice(total);
		dto.setList(list);
		
		return dto;	
	}


	//7th API of GET API in which filter (sorting) will be perform
	@GetMapping("/filter-courses")
	public CourseDto getCourses(
			@RequestParam(value="page",required=false,defaultValue="0") Integer page, 
			@RequestParam(value="size",required=false,defaultValue="1000") Integer size, 
			@RequestParam(value="sort",required=false,defaultValue="ASC") String direction,
			@RequestParam(value="sortParam",required=false,defaultValue="price") String sortParam){
		//4 parameter page, size, sort (defaultValue ASC) & sortParam (other than price sorting)
		CourseDto dto = new CourseDto();
		double total= 0;
		
		Sort sort; //ASC or DESC condition check
					//other parameter (except price) is also sort
		if(direction.equals("ASC")){
			sort = Sort.by(Sort.Direction.ASC,sortParam);
		}
		else{
			sort = Sort.by(Sort.Direction.DESC,sortParam);
		}
		
		Pageable pageable = PageRequest.of(page, size, sort);
		List<Course> list =  courseRepository.findAll(pageable).getContent();
		
		for(Course c : list){
			total = total + c.getPrice();
		}
		total = total - (total * 0.1); //10% discount
		
		dto.setDiscountPrice(total);
		dto.setList(list);
		
		return dto;	
	}
	
	// 9th GET API Based on Course price fetch all courses
	@GetMapping("/course/price/{price}")
	public List<Course> getCourseList(@PathVariable("price") double price) {
		return courseRepository.findAllByPrice(price);
	}
	
	// 10th GET API Fetch all courses based on Instructor name
	@GetMapping("/course/instructor/{name}")
	public List<Course> getCourseListByInstructorName(@PathVariable("name") String name) {
		return courseRepository.findAllByInstructorName(name);
	}
}
