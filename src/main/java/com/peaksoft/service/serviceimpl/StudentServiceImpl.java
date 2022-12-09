package com.peaksoft.service.serviceimpl;

import com.peaksoft.student.StudentConverterRequest;
import com.peaksoft.student.StudentConverterResponse;
import com.peaksoft.dto.request.StudentRequest;
import com.peaksoft.dto.response.StudentResponse;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Instructor;
import com.peaksoft.entity.Student;
import com.peaksoft.repository.GroupRepository;
import com.peaksoft.repository.StudentRepository;
import com.peaksoft.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentConverterResponse studentConverterResponse;

    private final StudentConverterRequest studentConverterRequest;

    private final GroupRepository groupRepository;

    @Override
    public List<StudentResponse> getAllIStudent() {
        return studentConverterResponse.getAll(studentRepository.findAll());
    }

    @Override
    public List<StudentResponse> getAllStudentIsGroup(Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        return studentConverterResponse.getAll(group.getStudents());
    }

    @Override
    public StudentResponse saveStudent(Long id, StudentRequest studentRequest) throws IOException {
        Group group = groupRepository.findById(id).get();
        for (Course course :group.getCourses()) {
            course.getCompany().plus();
        }
        for (Course course:group.getCourses()) {
            for (Instructor instructor : course.getInstructors()) {
                instructor.plus();
            }
        }
        Student student = studentConverterRequest.create(studentRequest);
        group.addStudent(student);
        student.setGroups(group);
        studentRepository.save(student);
        return studentConverterResponse.create(student);
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id).get();
        return studentConverterResponse.create(student);
    }

    @Override
    public StudentResponse updateStudent(StudentRequest studentRequest, Long id) throws IOException {
        Student student = studentRepository.findById(id).get();
        studentConverterRequest.update(student,studentRequest);
        return studentConverterResponse.create(studentRepository.save(student));
    }

    @Override
    public StudentResponse deleteStudent(Long id) {
        Student student = studentRepository.findById(id).get();
        for (Course course:student.getGroups().getCourses()) {
            course.getCompany().minus();
        }
        for (Course course:student.getGroups().getCourses()) {
            for (Instructor instructor:course.getInstructors()) {
                instructor.minus();
            }
        }
        studentRepository.delete(student);
        return studentConverterResponse.create(student);
    }

    @Override
    public StudentResponse assignStudentIsGroup(Long studentId, Long groupId) throws IOException {
        Student student = studentRepository.findById(studentId).get();
        Group group = groupRepository.findById(groupId).get();
        if (group.getStudents() != null) {
            for (Student s : group.getStudents()) {
                if (s.getId() == studentId) {
                    throw new IOException("This student already exists!");
                }
            }
        }
        student.setGroups(group);
        group.assignStudent(student);
        studentRepository.save(student);
        groupRepository.save(group);
        return studentConverterResponse.create(student);
    }
}
