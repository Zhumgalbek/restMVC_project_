package com.peaksoft.converter.lesson;

import com.peaksoft.dto.response.LessonResponse;
import com.peaksoft.entity.Lesson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class LessonConverterResponse {

    public LessonResponse create(Lesson lesson) {
        if (lesson == null) return null;
        LessonResponse lessonResponse = new LessonResponse();
        lessonResponse.setId(lesson.getId());
        lessonResponse.setLessonName(lesson.getLessonName());

        return lessonResponse;
    }

    public List<LessonResponse> getAll(List<Lesson> lessons) {
        List<LessonResponse> lessonResponses = new ArrayList<>();
        for (Lesson lesson : lessons) {
            lessonResponses.add(create(lesson));
        }
        return lessonResponses;
    }
}
