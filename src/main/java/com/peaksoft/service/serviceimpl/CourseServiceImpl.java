package com.peaksoft.service.serviceimpl;

import com.peaksoft.converter.course.CourseConvertRequest;
import com.peaksoft.converter.course.CourseConvertResponse;
import com.peaksoft.dto.request.CourseRequest;
import com.peaksoft.dto.response.CourseResponse;
import com.peaksoft.entity.Company;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.repository.CompanyRepository;
import com.peaksoft.repository.CourseRepository;
import com.peaksoft.repository.GroupRepository;
import com.peaksoft.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CompanyRepository companyRepository;

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final CourseConvertRequest courseConvertRequest;
    private final CourseConvertResponse courseConvertResponse;

    @Override
    public List<CourseResponse> getAllCourse() {
        return courseConvertResponse.getAll(courseRepository.findAll());
    }

    @Override
    public List<CourseResponse> getAllCourse(Long companyId) {
        Company company = companyRepository.findById(companyId).get();
        return courseConvertResponse.getAll(company.getCourses());
    }

    @Override
    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id).get();
        return courseConvertResponse.create(course);
    }

    @Override
    public CourseResponse saveCourse(Long companyId, CourseRequest courseRequest) throws IOException {
        Company company = companyRepository.findById(companyId).get();
        Course course = courseConvertRequest.create(courseRequest);
        company.addCourse(course);
        course.setCompany(company);
        courseRepository.save(course);
        return courseConvertResponse.create(course);
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseRequest courseRequest) {
        Course course = courseRepository.findById(id).get();
        courseConvertRequest.update(course, courseRequest);
        return courseConvertResponse.create(courseRepository.save(course));
    }

    @Override
    public CourseResponse deleteCourse(Long groupId,Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        Group group = groupRepository.findById(groupId).get();
        group.remove(course);
        courseRepository.delete(course);
        return courseConvertResponse.create(course);
    }

}
