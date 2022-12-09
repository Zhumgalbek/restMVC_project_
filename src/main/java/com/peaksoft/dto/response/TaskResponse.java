package com.peaksoft.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    private Long id;

    private String taskName;

    private String taskText;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadLine;
}
