package com.taskmaster.service;

import com.taskmaster.entity.Project;
import com.taskmaster.exception.ResourceNotFoundException;
import com.taskmaster.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional(readOnly = true)
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

    @Transactional
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Transactional
    public void deleteProject(Long id) {
        Project project = getProjectById(id);
        projectRepository.delete(project);
    }

    @Transactional(readOnly = true)
    public List<Project> searchProjectsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return getAllProjects();
        }
        return projectRepository.findByProjectNameContainingIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public long getProjectCount() {
        return projectRepository.count();
    }
}
