package com.peaksoft.controller;

import com.peaksoft.dto.request.StudentRequest;
import com.peaksoft.dto.response.StudentResponse;
import com.peaksoft.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/getAllStudents")
    @PreAuthorize("isAuthenticated()")
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllIStudent();
    }


    @GetMapping("/getStudentByIdIsGroup/{id}")
    @PreAuthorize("isAuthenticated()")
    public List<StudentResponse> getStudentByIdIsGroup(@PathVariable Long id) {
        return studentService.getAllStudentIsGroup(id);
    }

    @PostMapping("/{groupId}/saveStudent")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public StudentResponse saveStudent(@PathVariable Long groupId, @RequestBody StudentRequest studentRequest) throws IOException {
        return studentService.saveStudent(groupId, studentRequest);
    }

    @PutMapping("/updateStudent/{id}")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public StudentResponse updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest) throws IOException {
        return studentService.updateStudent(studentRequest, id);
    }

    @DeleteMapping("/deleteStudentById/{id}")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public StudentResponse deleteStudentById(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/getStudentById/{id}")
    @PreAuthorize("isAuthenticated()")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/assignStudentIsGroup/{studentId}/{groupId}")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public StudentResponse assignStudentIsGroup(@PathVariable Long studentId, @PathVariable Long groupId) throws IOException {
        return studentService.assignStudentIsGroup(studentId,groupId);
    }
}
