package com.taskmaster.config;

import com.taskmaster.entity.Project;
import com.taskmaster.entity.Task;
import com.taskmaster.entity.User;
import com.taskmaster.repository.ProjectRepository;
import com.taskmaster.repository.TaskRepository;
import com.taskmaster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public DatabaseSeeder(ProjectRepository projectRepository, TaskRepository taskRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Seed default user if none exists
        if (userRepository.count() == 0) {
            userRepository.save(new User("admin", "admin"));
        }

        if (projectRepository.count() == 0) {
            LocalDate today = LocalDate.now();

            // Seed Projects
            Project p1 = new Project("Website Redesign", "Rebuilding the corporate website with premium themes.", today.minusDays(10), today.plusDays(30), "In Progress");
            Project p2 = new Project("Mobile App Development", "Building an iOS and Android task tracker app.", today.minusDays(5), today.plusDays(45), "In Progress");
            Project p3 = new Project("Marketing Campaign", "Launching Q3 marketing efforts and ads.", today.minusDays(2), today.plusDays(15), "Not Started");
            Project p4 = new Project("Database Migration", "Migrating legacy system data to cloud MySQL server.", today.minusDays(20), today.minusDays(2), "Completed");
            Project p5 = new Project("AI Integration Study", "Researching integration of machine learning APIs.", today, today.plusDays(60), "Not Started");

            projectRepository.save(p1);
            projectRepository.save(p2);
            projectRepository.save(p3);
            projectRepository.save(p4);
            projectRepository.save(p5);

            // Seed Tasks for Project 1
            Task t1 = new Task("Design UI mockups", "Create wireframes and final mockups in Figma.", today.plusDays(2), "High", "In Progress", p1);
            Task t2 = new Task("Setup frontend boilerplate", "Configure HTML templates and basic CSS framework.", today.plusDays(5), "Medium", "Completed", p1);
            Task t3 = new Task("Implement database layer", "Create models, repositories, and migration scripts.", today.plusDays(10), "High", "Completed", p1);

            // Seed Tasks for Project 2
            Task t4 = new Task("Create API endpoints", "Expose REST endpoints for managing task status.", today.plusDays(8), "High", "In Progress", p2);
            Task t5 = new Task("Setup push notifications", "Integrate Firebase Cloud Messaging for tasks.", today.plusDays(20), "Medium", "Pending", p2);
            Task t6 = new Task("App store submission", "Submit build for verification on Apple App Store.", today.plusDays(40), "Low", "Pending", p2);

            // Seed Tasks for Project 3
            Task t7 = new Task("Create social media banners", "Design and export social media banner graphics.", today.plusDays(4), "Medium", "Pending", p3);
            Task t8 = new Task("Write newsletter copy", "Draft email newsletter for client subscribers.", today.plusDays(6), "Low", "Pending", p3);
            Task t9 = new Task("Setup AdWords campaigns", "Define target keywords and budgets.", today.plusDays(12), "High", "Pending", p3);

            // Seed Tasks for Project 4
            Task t10 = new Task("Export legacy database schema", "Generate SQL dump from old Oracle DB.", today.minusDays(18), "High", "Completed", p4);
            Task t11 = new Task("Write migration scripts", "Convert schemas and perform data cleanup.", today.minusDays(12), "High", "Completed", p4);
            Task t12 = new Task("Verify data integrity", "Run checksums on migrated database rows.", today.minusDays(3), "Medium", "Completed", p4);

            // Seed Tasks for Project 5
            Task t13 = new Task("Review OpenAI APIs", "Evaluate pricing, latency, and capabilities.", today.plusDays(10), "Medium", "In Progress", p5);
            Task t14 = new Task("Build prototype chatbot", "Implement simple prompt chain using LangChain.", today.plusDays(25), "High", "Pending", p5);
            Task t15 = new Task("Write API summary report", "Document findings and architectural recommendations.", today.plusDays(50), "Low", "Pending", p5);

            taskRepository.save(t1);
            taskRepository.save(t2);
            taskRepository.save(t3);
            taskRepository.save(t4);
            taskRepository.save(t5);
            taskRepository.save(t6);
            taskRepository.save(t7);
            taskRepository.save(t8);
            taskRepository.save(t9);
            taskRepository.save(t10);
            taskRepository.save(t11);
            taskRepository.save(t12);
            taskRepository.save(t13);
            taskRepository.save(t14);
            taskRepository.save(t15);
        }
    }
}
