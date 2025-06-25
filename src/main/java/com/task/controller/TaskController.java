package com.task.controller;
import com.task.dto.TaskDto;
import com.task.model.Task;
import com.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/save")
    public ResponseEntity<Task> createTask(@RequestBody TaskDto dto) {
        Task createdTask = taskService.createTask(dto);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<Task>> getTasksForEmployee(@PathVariable Long id) {
        return ResponseEntity.ok((List<Task>) taskService.getTasksForEmployee(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDto dto) {
        Task updatedTask = taskService.updateTask(id, dto);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }
}

