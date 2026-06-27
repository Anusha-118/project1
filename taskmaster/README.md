# TaskMaster – Project and Task Management System

TaskMaster is a beginner-friendly, full-stack Project and Task Management web application. Built using **Spring Boot**, **Spring Data JPA**, **Thymeleaf**, **MySQL**, and **Bootstrap 5**, it provides an elegant and intuitive dashboard to manage projects and their respective tasks. 

This project is structured specifically to serve as a high-quality showcase for portfolio reviews, college projects, and technical interview demonstrations, keeping the architecture straightforward, clean, and easy to explain.

---

## 🌟 Features

### 1. Interactive Dashboard
- Visual metrics displaying **Total Projects**, **Total Tasks**, **Completed Tasks**, and **Pending Tasks**.
- Color-coded statistical cards with sleek hover effects.
- Quick navigation shortcuts.

### 2. Project Management (CRUD)
- Create, view, edit, and delete projects.
- Keep track of project attributes: Name, Description, Start Date, End Date, and Status (*Not Started*, *In Progress*, *Completed*).

### 3. Task Management (CRUD)
- Create, view, edit, and delete tasks.
- Associate each task to a specific project (One-to-Many relationship).
- Track task details: Name, Description, Due Date, Priority (*Low*, *Medium*, *High*), and Status (*Pending*, *In Progress*, *Completed*).

### 4. Search and Filters
- Instant search filters for Projects by Name.
- Instant search filters for Tasks by Name.

### 5. Input Validation
- Built-in validation rules using Spring Starter Validation.
- Helpful UI error highlights on project/task forms for required fields (Project Name, Task Name, and Due Date).

### 6. Auto-Data Seeding
- The database is automatically populated with **5 sample projects** and **15 sample tasks** on the first run, allowing for immediate testing.

### 7. Session-Based Authentication (Mock Login)
- Pre-configured login system using simple server-side HTTP sessions (`HttpSession`).
- Interceptor protection redirects unauthenticated requests automatically to the login page.
- Visual dynamic welcome header and **Logout** button.

### 8. User-Friendly Error Handling
- Custom mapping for common HTTP status codes (such as **404 Not Found** and **500 Internal Server Error**) to present professional, styled error pages.

---

## 🛠️ Tech Stack

- **Backend:** Java 21, Spring Boot 3.3.0, Spring Data JPA, Hibernate
- **Frontend:** HTML5, Thymeleaf, Vanilla CSS, Bootstrap 5, Bootstrap Icons
- **Database:** MySQL
- **Build Tool:** Maven

---

## 📂 Folder Structure

The project follows a standard Spring Boot maven layout:

```text
TaskMaster/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── taskmaster/
│   │   │           ├── TaskMasterApplication.java  # Main Application class
│   │   │           ├── config/
│   │   │           │   ├── DatabaseSeeder.java     # Seeds 5 projects, 15 tasks on startup
│   │   │           │   └── WebMvcConfig.java       # Registers LoginInterceptor
│   │   │           ├── controller/
│   │   │           │   ├── DashboardController.java # Maps dashboard metrics
│   │   │           │   ├── ProjectController.java   # Project CRUD controller
│   │   │           │   ├── TaskController.java      # Task CRUD controller
│   │   │           │   ├── LoginController.java     # Session login/logout endpoints
│   │   │           │   ├── LoginInterceptor.java    # Redirects unauthenticated requests
│   │   │           │   └── CustomErrorController.java# Catch-all custom error mapper
│   │   │           ├── entity/
│   │   │           │   ├── Project.java            # Project entity (1:N Tasks)
│   │   │           │   └── Task.java               # Task entity (N:1 Project)
│   │   │           ├── exception/
│   │   │           │   └── ResourceNotFoundException.java # Custom exception (404 mapped)
│   │   │           ├── repository/
│   │   │           │   ├── ProjectRepository.java  # Project JPA Repository interface
│   │   │           │   └── TaskRepository.java     # Task JPA Repository interface
│   │   │           └── service/
│   │   │               ├── ProjectService.java     # Project Business Logic Layer
│   │   │               └── TaskService.java        # Task Business Logic Layer
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   └── css/
│   │       │       └── styles.css              # Custom CSS overlaying Bootstrap
│   │       ├── templates/
│   │       │   ├── fragments/
│   │       │   │   ├── navbar.html             # Top-bar Thymeleaf fragment
│   │       │   │   └── footer.html             # Page footer Thymeleaf fragment
│   │       │   ├── index.html                  # Alias Dashboard template
│   │       │   ├── dashboard.html              # Core Dashboard visualization
│   │       │   ├── login.html                  # Session login credentials page
│   │       │   ├── projects.html               # List projects table and search
│   │       │   ├── add-project.html            # Add project form page
│   │       │   ├── edit-project.html           # Edit project form page
│   │       │   ├── tasks.html                  # List tasks table and search
│   │       │   ├── add-task.html               # Add task form page
│   │       │   ├── edit-task.html              # Edit task form page
│   │       │   └── error.html                  # Clean 404/500 template
│   │       └── application.properties          # Database config & Server settings
│
└── pom.xml                                     # Maven Dependencies definition
```

---

## ⚙️ Setup and Installation

### 1. Prerequisites
- **Java SDK 21** or later installed.
- **Maven** installed (or use the packaged wrapper if available, otherwise standard `mvn` commands).
- **MySQL Server** running locally.

### 2. Database Configuration
1. Open your MySQL client (e.g., MySQL Workbench, Command Line, DBeaver).
2. Execute the following command to create the database schema:
   ```sql
   CREATE DATABASE taskmaster_db;
   ```
3. The project is pre-configured in `src/main/resources/application.properties` to connect to `localhost:3306` with the username `root` and password `root`. If your MySQL configuration has a different password, edit the properties file accordingly:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/taskmaster_db
   spring.datasource.username=root
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   ```

### 3. Build & Run
From the root directory containing `pom.xml`, run the following commands:

```bash
# Clean and compile the codebase
mvn clean compile

# Launch the Spring Boot application
mvn spring-boot:run
```

Once you see the console message `Started TaskMasterApplication in X.XXX seconds`, open your browser and navigate to:
👉 **[http://localhost:8080](http://localhost:8080)**

Since interceptor protection is active, you will be redirected to the login screen. 
🔑 Please log in using the demo credentials:
*   **Username:** `admin`
*   **Password:** `admin`

---

## 📸 Screenshots

*(Place screen captures here after running the application)*
- **Dashboard:** `[Placeholder: Dashboard screenshot showcasing statistic cards]`
- **Projects List:** `[Placeholder: Projects list layout showing search and actions]`
- **Tasks List:** `[Placeholder: Tasks list layout showing project links and statuses]`

---

## 🔮 Future Enhancements

If you wish to expand the application further, consider these next steps:
1. **User Authentication & Authorization:** Integrate Spring Security to handle roles (User, Admin) and login screens.
2. **REST API Layer:** Implement `@RestController` endpoints to serve JSON data for mobile apps or React/Angular frontends.
3. **Interactive Kanban Board:** Add a drag-and-drop interface for task statuses (Pending ➔ In Progress ➔ Completed).
4. **Email Reminders:** Set up automated email reminders using Spring Mail for tasks past their due dates.
