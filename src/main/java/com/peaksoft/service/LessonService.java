package com.peaksoft.service;

import com.peaksoft.dto.request.CompanyRequest;
import com.peaksoft.dto.request.LessonRequest;
import com.peaksoft.dto.response.CompanyResponse;
import com.peaksoft.dto.response.LessonResponse;

import java.util.List;

public interface LessonService {

    List<LessonResponse> getAllLesson();

    LessonResponse getLessonById(Long id);

    LessonResponse saveLesson(LessonRequest lessonRequest, Long id);

    LessonResponse updateLesson(Long id, LessonRequest lessonRequest);

    LessonResponse deleteLessonById(Long id);
}
