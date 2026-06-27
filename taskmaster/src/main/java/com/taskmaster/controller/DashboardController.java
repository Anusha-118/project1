package com.taskmaster.controller;

import com.taskmaster.service.ProjectService;
import com.taskmaster.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final ProjectService projectService;
    private final TaskService taskService;

    @Autowired
    public DashboardController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping({"/", "/dashboard"})
    public String viewDashboard(Model model) {
        model.addAttribute("totalProjects", projectService.getProjectCount());
        model.addAttribute("totalTasks", taskService.getTaskCount());
        model.addAttribute("completedTasks", taskService.getCompletedTaskCount());
        model.addAttribute("pendingTasks", taskService.getPendingTaskCount());
        model.addAttribute("activePage", "dashboard");
        return "dashboard";
    }
}
