package com.peaksoft.controller;

import com.peaksoft.dto.request.CourseRequest;
import com.peaksoft.dto.response.CourseResponse;
import com.peaksoft.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/getAllCourse")
    @PreAuthorize("isAuthenticated()")
    public List<CourseResponse> getAllCourse() {
        return courseService.getAllCourse();
    }

    @GetMapping("/getAllCourseByCompanyId/{companyId}")
    @PreAuthorize("isAuthenticated()")
    public List<CourseResponse> getAllCourseByCompanyId(@PathVariable Long companyId) {
        return courseService.getAllCourse(companyId);
    }

    @GetMapping("/getCourseById/{id}")
    @PreAuthorize("isAuthenticated()")
    public CourseResponse getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping("/{companyId}/saveCourse")
    @PreAuthorize("hasAuthority('Admin')")
    public CourseResponse saveCourse(@PathVariable Long companyId, @RequestBody CourseRequest courseRequest) throws IOException {
        return courseService.saveCourse(companyId, courseRequest);
    }

    @PutMapping("/updateCourse/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public CourseResponse updateCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(id, courseRequest);
    }

    @DeleteMapping("/deleteCourseById/{groupId}/{courseId}")
    @PreAuthorize("hasAuthority('Admin')")
    public CourseResponse deleteCourseById(@PathVariable Long groupId, @PathVariable Long courseId) {
        return courseService.deleteCourse(groupId,courseId);
    }



}
