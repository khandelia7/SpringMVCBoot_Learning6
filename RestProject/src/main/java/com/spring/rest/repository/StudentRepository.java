package com.spring.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.rest.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	// JPQL
	@Query("select s from Student s join s.courses c join c.instructor i where i.id=?1")
	List<Student> fetchStudentsByInstructorName(Long id); 
}
