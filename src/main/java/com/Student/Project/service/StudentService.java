package com.Student.Project.service;

import com.Student.Project.Dto.StudentRequestDto;
import com.Student.Project.Dto.StudentResponseDto;

import java.util.List;

public interface StudentService {
    StudentResponseDto createStudent(StudentRequestDto requestDto);
    StudentResponseDto getStudentByRollNo(String rollNo);
    List<StudentResponseDto> getAllStudents();
    StudentResponseDto updateStudentByRollNo(String rollNo,StudentRequestDto requestDto);
    void deleteStudentByRollNo(String rollNo);
}
