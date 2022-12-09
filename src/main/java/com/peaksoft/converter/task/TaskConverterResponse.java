package com.peaksoft.converter.task;

import com.peaksoft.dto.response.TaskResponse;
import com.peaksoft.entity.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskConverterResponse {

    public TaskResponse create(Task task) {
        if (task == null) return null;
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTaskName(task.getTaskName());
        taskResponse.setTaskText(task.getTaskText());
        taskResponse.setDeadLine(task.getDeadLine());

        return taskResponse;
    }

    public List<TaskResponse> getAll(List<Task> tasks) {
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task : tasks) {
            taskResponses.add(create(task));
        }
        return taskResponses;
    }
}
