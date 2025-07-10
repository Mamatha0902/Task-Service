package com.task.service;

import com.task.dto.TaskDto;
import com.task.model.Task;
import com.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(TaskDto dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus() != null ? dto.getStatus() : "PENDING");
        task.setDeadline(dto.getDeadline());
        task.setEmployeeId(dto.getEmployeeId());

        return taskRepository.save(task);
    }

    public List<Task> getTasksForEmployee(Long empId) {
        return taskRepository.findByEmployeeId(empId);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, Task dto) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(dto.getTitle());
            task.setDescription(dto.getDescription());
            task.setStatus(dto.getStatus());
            task.setDeadline(dto.getDeadline());
            task.setEmployeeId(dto.getEmployeeId());
            return taskRepository.save(task);
        }
        return null;
    }

    public void assignTask(Long taskId, Long empId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setEmployeeId(empId);
        taskRepository.save(task);
    }

    public Task getTaskByTaskId(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskId));
    }
}
