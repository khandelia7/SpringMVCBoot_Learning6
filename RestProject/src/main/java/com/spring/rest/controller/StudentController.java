package com.spring.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.dto.StudentDto;
import com.spring.rest.model.Course;
import com.spring.rest.model.Student;
import com.spring.rest.repository.CourseRepository;
import com.spring.rest.repository.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	// Put student details
	@PostMapping("/student")
	public Student postStudent(@RequestBody Student student){
		return studentRepository.save(student);
	}
	
	// Get students details
	@GetMapping("/student")
	public List<Student> getALlStudents(){
		return studentRepository.findAll();
	}
	
	// student enrol course API
	@PostMapping("/student/{sid}/course/{cid}")
	public void enrollStudentToCourse(@PathVariable("sid") Long sid
			,@PathVariable("cid") Long cid ){
		
		// get student ID
		Student student = studentRepository.getOne(sid);//HP
		// get course ID
		Course course = courseRepository.getOne(cid);//ML
		
		/* Over write problem  & repetition problem
		 * List<Course> listCourse = student.getCourses();
		 * listCourse.add(course);
		 * student.setCourses(listCourse);
		 * studentRepository.save(student);
		 */
		
		/*ConcurrentModificationException
		 * fail-fast when something we are iterating on is modified. 
		 */
		
		boolean status = false;
		List<Course> listCourse = student.getCourses();
		
		for(Course c : listCourse){
			if(c.getId().equals(cid)){
				status = true;
				break;
			}
		}
		if(status == false){
			listCourse.add(course);
		}
		student.setCourses(listCourse);
		studentRepository.save(student);
	}
	
	// student un-enrol course API
	@PutMapping("/student/{sid}/course/{cid}")
	public Student unenroll(@PathVariable("sid") Long sid
			,@PathVariable("cid") Long cid ){
		
		Student student = studentRepository.getOne(sid);//HP
		
		List<Course> listCourse = student.getCourses();
		//IMPORTANT #Course.java
		Set<Course> set = new TreeSet<>();
		
		for(Course c : listCourse){
			set.add(c);
		}
		
		for(Course c : listCourse){
			if(c.getId().equals(cid)){
				set.remove(c);
			}
		}
		
		listCourse = set.stream()
				.collect(Collectors.toList());
		
		student.setCourses(listCourse);
		return  studentRepository.save(student);
	}
	
	//Fetch All students who are taught by instructor having id: 6/7
	@GetMapping("/student/instructor/{id}")
	public List<StudentDto> fetchStudentsByInstructorName (@PathVariable("id") Long id){
		// go to StudentRepo & write native or JPQL for this operation
		List<Student> list = studentRepository.fetchStudentsByInstructorName(id);
		
		//StudentDto
		List<StudentDto> listDto = new ArrayList<>();
		list.stream().forEach(s->{ StudentDto dto = new StudentDto();
								   dto.setId(s.getId());
								   dto.setStudentName(s.getName());
								   dto.setCourseName(
										            s.getCourses().stream().map(c->c.getName()) 
										            .collect(Collectors.toList())
										            );
								   listDto.add(dto);
							  });
		
		return listDto;
	}
	
}
