package com.taskmaster.controller;

import com.taskmaster.entity.Project;
import com.taskmaster.entity.Task;
import com.taskmaster.service.ProjectService;
import com.taskmaster.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;

    @Autowired
    public TaskController(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @GetMapping
    public String listTasks(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Task> tasks = taskService.searchTasksByName(search);
        model.addAttribute("tasks", tasks);
        model.addAttribute("search", search);
        model.addAttribute("activePage", "tasks");
        return "tasks";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("activePage", "tasks");
        return "add-task";
    }

    @PostMapping("/add")
    public String addTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("projects", projectService.getAllProjects());
            model.addAttribute("activePage", "tasks");
            return "add-task";
        }
        // Fetch and set the managed Project entity to ensure clean mapping
        if (task.getProject() != null && task.getProject().getId() != null) {
            Project project = projectService.getProjectById(task.getProject().getId());
            task.setProject(project);
        }
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("activePage", "tasks");
        return "edit-task";
    }

    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable("id") Long id, @Valid @ModelAttribute("task") Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("projects", projectService.getAllProjects());
            model.addAttribute("activePage", "tasks");
            return "edit-task";
        }
        if (task.getProject() != null && task.getProject().getId() != null) {
            Project project = projectService.getProjectById(task.getProject().getId());
            task.setProject(project);
        }
        task.setId(id);
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}
