package com.Student.Project.controller;

import com.Student.Project.Dto.StudentRequestDto;
import com.Student.Project.Dto.StudentResponseDto;
import com.Student.Project.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Student")
public class StudentController {
    public final StudentService studentService;
    //Create Student
    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto requestDto){
        StudentResponseDto response = studentService.createStudent(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Get Student By RollNo
    @GetMapping("/{rollNo}")
    public ResponseEntity<StudentResponseDto> getStudentByRollNo(@PathVariable String rollNo){
        StudentResponseDto response = studentService.getStudentByRollNo(rollNo);
        return ResponseEntity.ok(response);
    }

    //Get All Students
    @GetMapping
    public ResponseEntity<List<StudentResponseDto>>  getAllStudents(){
        List<StudentResponseDto> response = studentService.getAllStudents();
        return ResponseEntity.ok(response);
    }

    //Update Student with roll no
    @PutMapping("/{rollNo}")
    public ResponseEntity<StudentResponseDto> updateStudentByRollNo(@PathVariable String rollNo,
                                                                    @Valid @RequestBody StudentRequestDto requestDto){
        StudentResponseDto response = studentService.updateStudentByRollNo(rollNo,requestDto);
        return ResponseEntity.ok(response);
    }

    //Delete Student with roll no
    @DeleteMapping("/{rollNo}")
    public ResponseEntity<Void> deleteStudentByRollNo(@PathVariable String rollNo){
        studentService.deleteStudentByRollNo(rollNo);
        return ResponseEntity.noContent().build();
    }
}
