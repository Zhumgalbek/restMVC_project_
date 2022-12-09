package com.peaksoft.converter.lesson;

import com.peaksoft.dto.request.LessonRequest;
import com.peaksoft.entity.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonConverterRequest {

    public Lesson create(LessonRequest lessonRequest) {
        if (lessonRequest == null) return null;
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.getLessonName());
        return lesson;
    }


    public void update(Lesson lesson, LessonRequest lessonRequest) {
        if (lessonRequest.getLessonName() != null) {
            lesson.setLessonName(lessonRequest.getLessonName());
        }

    }
}
