package com.Student.Project.repository;

import com.Student.Project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long>{

    Optional<Student> findByRollNo(String rollNo);
    boolean existsByRollNo(String rollNo);

     boolean existsByEmail(String email);
}
