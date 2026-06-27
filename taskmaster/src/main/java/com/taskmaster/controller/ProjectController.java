package com.taskmaster.controller;

import com.taskmaster.entity.Project;
import com.taskmaster.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public String listProjects(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Project> projects = projectService.searchProjectsByName(search);
        model.addAttribute("projects", projects);
        model.addAttribute("search", search);
        model.addAttribute("activePage", "projects");
        return "projects";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("activePage", "projects");
        return "add-project";
    }

    @PostMapping("/add")
    public String addProject(@Valid @ModelAttribute("project") Project project, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "projects");
            return "add-project";
        }
        projectService.saveProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        model.addAttribute("activePage", "projects");
        return "edit-project";
    }

    @PostMapping("/edit/{id}")
    public String updateProject(@PathVariable("id") Long id, @Valid @ModelAttribute("project") Project project, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "projects");
            return "edit-project";
        }
        project.setId(id);
        projectService.saveProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects";
    }
}
