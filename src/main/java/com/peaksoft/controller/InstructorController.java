package com.peaksoft.controller;

import com.peaksoft.dto.request.InstructorRequest;
import com.peaksoft.dto.response.InstructorResponse;
import com.peaksoft.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/getAllInstructors")
    @PreAuthorize("isAuthenticated()")
    public List<InstructorResponse> getAllInstructor() {
        return instructorService.getAllInstructor();
    }


    @GetMapping("/getAllInstructorIsCourse/{id}")
    @PreAuthorize("isAuthenticated()")
    public List<InstructorResponse> getInstructorByIdIsCourse(@PathVariable Long id) {
        return instructorService.getAllInstructorIsCourse(id);
    }

    @PostMapping("/{courseId}/saveInstructor")
    @PreAuthorize("hasAnyAuthority('Admin')")
    public InstructorResponse saveInstructor(@PathVariable Long courseId, @RequestBody InstructorRequest instructorRequest) throws IOException {
        return instructorService.saveInstructor(courseId, instructorRequest);
    }

    @PutMapping("/updateInstructor/{id}")
    @PreAuthorize("hasAnyAuthority('Admin')")
    public InstructorResponse updateInstructor(@PathVariable Long id, @RequestBody InstructorRequest instructorRequest) throws IOException {
        return instructorService.updateInstructor(instructorRequest, id);
    }

    @DeleteMapping("/deleteInstructorById/{id}")
    @PreAuthorize("hasAnyAuthority('Admin')")
    public InstructorResponse deleteInstructorById(@PathVariable Long id) {
        return instructorService.deleteInstructor(id);
    }

    @RequestMapping("/getInstructorById/{id}")
    @PreAuthorize("isAuthenticated()")
    public InstructorResponse getInstructorById(@PathVariable Long id) {
        return instructorService.getInstructorById(id);
    }

    @PostMapping("/assignInstructorIsCourse/{courseId}/{instructorId}")
    @PreAuthorize("hasAnyAuthority('Admin')")
    public InstructorResponse assignInstructorCourse(@PathVariable Long instructorId, @PathVariable Long courseId) throws IOException {
        return instructorService.assignInstructor(courseId,instructorId);
    }
}
