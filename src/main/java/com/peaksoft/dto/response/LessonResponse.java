package com.peaksoft.dto.response;

import com.peaksoft.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonResponse {

    private Long id;

    private String lessonName;

    private Course course;
}
