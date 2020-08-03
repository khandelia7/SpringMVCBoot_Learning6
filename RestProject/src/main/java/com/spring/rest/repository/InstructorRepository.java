package com.spring.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.rest.model.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long>{

    // native query
	@Query(value= "SELECT instructor.name from Course, instructor where course.instructor_id = instructor.id AND course.course_name=?1" 
			,nativeQuery=true)
    String  getInstructorByCourseName(String cname);

	// JPQL
	@Query("select i from Course c join c.instructor i where c.name=?1")
	List<Instructor> getInstructorByCourseNameJPQL(String cname);
	
}
