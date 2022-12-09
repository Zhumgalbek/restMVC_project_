package com.peaksoft.service.serviceimpl;

import com.peaksoft.converter.lesson.LessonConverterRequest;
import com.peaksoft.converter.lesson.LessonConverterResponse;
import com.peaksoft.dto.request.LessonRequest;
import com.peaksoft.dto.response.LessonResponse;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Lesson;
import com.peaksoft.repository.CourseRepository;
import com.peaksoft.repository.LessonRepository;
import com.peaksoft.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    private final LessonConverterRequest lessonConverterRequest;

    private final LessonConverterResponse lessonConverterResponse;

    private final CourseRepository courseRepository;


    @Override
    public List<LessonResponse> getAllLesson() {
        return lessonConverterResponse.getAll(lessonRepository.findAll());
    }

    @Override
    public LessonResponse getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id).get();
        return lessonConverterResponse.create(lesson);
    }

    @Override
    public LessonResponse saveLesson(LessonRequest lessonRequest, Long id) {
        Course course = courseRepository.findById(id).get();
        Lesson lesson = lessonConverterRequest.create(lessonRequest);
        course.addLesson(lesson);
        lesson.setCourse(course);
        lessonRepository.save(lesson);
        return lessonConverterResponse.create(lesson);
    }

    @Override
    public LessonResponse updateLesson(Long id, LessonRequest lessonRequest) {
        Lesson lesson = lessonRepository.findById(id).get();
        lessonConverterRequest.update(lesson,lessonRequest);
        return lessonConverterResponse.create(lessonRepository.save(lesson));
    }

    @Override
    public LessonResponse deleteLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id).get();
        lessonRepository.delete(lesson);
        return lessonConverterResponse.create(lesson);
    }
}
