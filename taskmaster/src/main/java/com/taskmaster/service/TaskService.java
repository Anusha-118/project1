package com.taskmaster.service;

import com.taskmaster.entity.Task;
import com.taskmaster.exception.ResourceNotFoundException;
import com.taskmaster.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    @Transactional
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    @Transactional(readOnly = true)
    public List<Task> searchTasksByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return getAllTasks();
        }
        return taskRepository.findByTaskNameContainingIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public long getTaskCount() {
        return taskRepository.count();
    }

    @Transactional(readOnly = true)
    public long getCompletedTaskCount() {
        return taskRepository.countByStatus("Completed");
    }

    @Transactional(readOnly = true)
    public long getPendingTaskCount() {
        return taskRepository.countByStatus("Pending");
    }
}
