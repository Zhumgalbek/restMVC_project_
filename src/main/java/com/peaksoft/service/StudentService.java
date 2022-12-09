package com.peaksoft.service;

import com.peaksoft.dto.request.InstructorRequest;
import com.peaksoft.dto.request.StudentRequest;
import com.peaksoft.dto.response.InstructorResponse;
import com.peaksoft.dto.response.StudentResponse;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    List<StudentResponse> getAllIStudent();

    List<StudentResponse> getAllStudentIsGroup(Long groupId);

    StudentResponse saveStudent(Long id, StudentRequest studentRequest) throws IOException;

    StudentResponse getStudentById(Long id);

    StudentResponse updateStudent(StudentRequest studentRequest, Long id) throws IOException;

    StudentResponse deleteStudent(Long id);

    StudentResponse assignStudentIsGroup(Long groupId, Long studentId) throws IOException;
}
