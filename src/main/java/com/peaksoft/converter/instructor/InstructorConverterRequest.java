package com.peaksoft.converter.instructor;

import com.peaksoft.dto.request.InstructorRequest;
import com.peaksoft.entity.Instructor;
import org.springframework.stereotype.Component;

@Component
public class InstructorConverterRequest {

    public Instructor create(InstructorRequest instructorRequest) {
        if (instructorRequest == null) return null;
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setEmail(instructorRequest.getEmail());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        return instructor;
    }

    public void update(Instructor instructor, InstructorRequest instructorRequest) {
        if (instructorRequest.getLastName() != null) {
            instructor.setLastName(instructorRequest.getLastName());
        }
        if (instructorRequest.getFirstName() != null) {
            instructor.setFirstName(instructorRequest.getFirstName());
        }
        if (instructorRequest.getEmail() != null) {
            instructor.setEmail(instructorRequest.getEmail());
        }
        if (instructorRequest.getPhoneNumber() != null) {
            instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        }
        if (instructorRequest.getSpecialization() != null) {
            instructor.setSpecialization(instructorRequest.getSpecialization());
        }
    }
}
