package com.peaksoft.service;

import com.peaksoft.dto.request.TaskRequest;
import com.peaksoft.dto.response.TaskResponse;

import java.io.IOException;
import java.util.List;

public interface TaskService {

    List<TaskResponse> getAllTasks();

    List<TaskResponse> getAllTask(Long lessonId);

    TaskResponse getTaskById(Long id);

    TaskResponse saveTask(Long lessonId, TaskRequest taskRequest) throws IOException;

    TaskResponse updateTask(Long id, TaskRequest taskRequest);

    TaskResponse deleteTask(Long id);
}
