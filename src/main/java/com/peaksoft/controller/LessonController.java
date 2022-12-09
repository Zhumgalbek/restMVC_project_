package com.peaksoft.controller;

import com.peaksoft.dto.request.LessonRequest;
import com.peaksoft.dto.response.LessonResponse;
import com.peaksoft.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/getAllLesson")
    @PreAuthorize("isAuthenticated()")
    public List<LessonResponse> getAllLesson() {
        return lessonService.getAllLesson();
    }

    @GetMapping("/getLessonById/{id}")
    @PreAuthorize("isAuthenticated()")
    public LessonResponse getLessonIsCourseById(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    @PostMapping("/saveLesson/{courseId}")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public LessonResponse saveCompany(@PathVariable Long courseId , @RequestBody LessonRequest lessonRequest ) {
        return lessonService.saveLesson(lessonRequest,courseId);
    }

    @PutMapping("/updateLesson/{id}")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public LessonResponse updateLesson(@PathVariable Long id, @RequestBody LessonRequest lessonRequest) {
        return lessonService.updateLesson(id, lessonRequest);
    }

    @DeleteMapping("/deleteLesson/{id}")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public LessonResponse deleteCompany(@PathVariable Long id) {
        return lessonService.deleteLessonById(id);
    }
}
