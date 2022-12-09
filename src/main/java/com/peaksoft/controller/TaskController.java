package com.peaksoft.controller;

import com.peaksoft.dto.request.TaskRequest;
import com.peaksoft.dto.response.TaskResponse;
import com.peaksoft.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/getAllTasks")
    @PreAuthorize("isAuthenticated()")
    public List<TaskResponse> getAllTask() {
        return taskService.getAllTasks();
    }

    @GetMapping("/getTaskById/{id}")
    @PreAuthorize("isAuthenticated()")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/getTaskIsLessonById/{id}")
    @PreAuthorize("isAuthenticated()")
    public List<TaskResponse> getLessonIsTaskById(@PathVariable Long id) {
        return taskService.getAllTask(id);
    }

    @PostMapping("/saveTask/{lessonId}")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public TaskResponse saveTask(@PathVariable Long lessonId , @RequestBody TaskRequest taskRequest ) throws IOException {
        return taskService.saveTask(lessonId, taskRequest);
    }

    @PutMapping("/updateTask/{id}")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(id, taskRequest);
    }

    @DeleteMapping("/deleteTask/{id}")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public TaskResponse deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }
}
