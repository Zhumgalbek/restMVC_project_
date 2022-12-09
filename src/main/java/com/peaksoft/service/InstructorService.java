package com.peaksoft.service;

import com.peaksoft.dto.request.InstructorRequest;
import com.peaksoft.dto.response.InstructorResponse;

import java.io.IOException;
import java.util.List;

public interface InstructorService {

    List<InstructorResponse> getAllInstructor();

    List<InstructorResponse> getAllInstructorIsCourse(Long courseId);

    InstructorResponse saveInstructor(Long id, InstructorRequest instructorRequest) throws IOException;

    InstructorResponse getInstructorById(Long id);

    InstructorResponse updateInstructor(InstructorRequest instructor, Long id) throws IOException;

    InstructorResponse deleteInstructor(Long id);

    InstructorResponse assignInstructor(Long courseId, Long instructorId) throws IOException;
}
