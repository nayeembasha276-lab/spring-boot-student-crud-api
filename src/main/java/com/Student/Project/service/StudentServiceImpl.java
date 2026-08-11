package com.Student.Project.service;

import com.Student.Project.Dto.StudentRequestDto;
import com.Student.Project.Dto.StudentResponseDto;
import com.Student.Project.entity.Student;
import com.Student.Project.exception.DuplicateEmailException;
import com.Student.Project.exception.DuplicateRollNoException;
import com.Student.Project.exception.StudentNotFoundException;
import com.Student.Project.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    // Create Student
    @Override
    public StudentResponseDto createStudent(StudentRequestDto requestDto) {
        //check Duplicate Roll Number
        if (studentRepository.existsByRollNo(requestDto.getRollNo())) {
            throw new DuplicateRollNoException("Student with RollNo: " + requestDto.getRollNo() + "Already exists");
        }
            //check Duplicate Email
            if (studentRepository.existsByRollNo(requestDto.getEmail())){
                throw new DuplicateEmailException("Student with Email: "+requestDto.getEmail()+"Already exists");
            }


        Student student = Student.builder()
                .rollNo(requestDto.getRollNo())
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .course(requestDto.getCourse())
                .mobile(requestDto.getMobile()).build();
                Student saveStudent = studentRepository.save(student);

        return mapToResponseDto(saveStudent);
    }

    //Get Student With Roll Number
    @Override
    public StudentResponseDto getStudentByRollNo(String rollNo) {
        Student student = studentRepository.findByRollNo(rollNo)
                .orElseThrow(() -> new StudentNotFoundException("Student With roll number"+rollNo+"not found"));
        return mapToResponseDto(student);
    }
//   Get All Students
    @Override
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this :: mapToResponseDto)
                .toList();
    }


    // Update Student With Roll Number
    @Override
    public StudentResponseDto updateStudentByRollNo(String rollNo, StudentRequestDto requestDto) {
        //Find exists Student
        Student student = studentRepository.findByRollNo(rollNo)
                .orElseThrow(() -> new StudentNotFoundException("Student with roll number"+rollNo+"not found"));

        //check if new roll number belongs to another student
        if (! student.getRollNo().equals(requestDto.getRollNo()) &&
                studentRepository.existsByRollNo(requestDto.getRollNo())){
            throw new DuplicateRollNoException("Roll number"+requestDto.getRollNo()+"already exists");
        }

        // new email another student
        if (! student.getEmail().equals(requestDto.getEmail())&&
                studentRepository.existsByEmail(requestDto.getEmail())){
            throw new DuplicateEmailException("Email"+requestDto.getEmail()+"already exists");
        }

        //  update fields
        student.setRollNo(requestDto.getRollNo());
        student.setName(requestDto.getName());
        student.setEmail(requestDto.getEmail());
        student.setCourse(requestDto.getCourse());
        student.setMobile(requestDto.getMobile());


        //save update student
        Student updateStudent = studentRepository.save(student);
        return mapToResponseDto(updateStudent);
    }


    // Delete By Student With Roll Number
    @Override
    public void deleteStudentByRollNo(String rollNo) {
        Student student = studentRepository.findByRollNo(rollNo)
                .orElseThrow(()-> new StudentNotFoundException("Student with roll number"+rollNo+"not found"));
        studentRepository.delete(student);

    }

    //Entity Response Dto
    private StudentResponseDto mapToResponseDto(Student student){
        return StudentResponseDto.builder()
                .id(student.getId())
                .rollNo(student.getRollNo())
                .name(student.getName())
                .email(student.getEmail())
                .course(student.getCourse())
                .mobile(student.getMobile()).build();

    }
}
