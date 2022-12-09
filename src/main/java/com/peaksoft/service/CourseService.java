package com.peaksoft.service;

import com.peaksoft.dto.request.CourseRequest;
import com.peaksoft.dto.response.CourseResponse;

import java.io.IOException;
import java.util.List;

public interface CourseService {

    List<CourseResponse> getAllCourse();

    List<CourseResponse> getAllCourse(Long companyId);

    CourseResponse getCourseById(Long id);

    CourseResponse saveCourse(Long companyId, CourseRequest course) throws IOException;

    CourseResponse updateCourse(Long id, CourseRequest course);

    CourseResponse deleteCourse(Long groupId,Long courseId);

}
