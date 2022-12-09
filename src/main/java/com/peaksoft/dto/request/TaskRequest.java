package com.peaksoft.dto.request;

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
public class TaskRequest {

    private String taskName;

    private String taskText;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadLine ;
}
