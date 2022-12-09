package com.peaksoft.dto.request;

import com.peaksoft.enums.StudyFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;

}
