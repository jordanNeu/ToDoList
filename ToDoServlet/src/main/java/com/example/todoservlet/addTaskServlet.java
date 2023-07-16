package com.example.todoservlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
// Simple Java To-DO List, using Hibernation, that allows a user to enter a number corresponding to
// the choice they'd like to make, entries will be added to our local database
// Features include: Adding new tasks, deleting existing tasks
// and listing all tasks

// Tag that Coordinates with .jsp file, used in place of web.xml file
@WebServlet(name = "addTask", urlPatterns = "/addTask")
// Main method for adding a task
public class addTaskServlet extends HttpServlet {
    // EntityManager library used for adding content to MySQL database
    private static EntityManagerFactory factory;

    public void init() throws ServletException {
        factory = Persistence.createEntityManagerFactory("tasks");
    }
    // Do-Post runs once accessed by JSP file, not able to get this connected
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DO Post Works");
        EntityManager entityManager = factory.createEntityManager();

        String taskDescription = request.getParameter("taskDescription");
        if (taskDescription != null && !taskDescription.isEmpty()) {
            Task newTask = new Task();
            newTask.setTaskDescription(taskDescription);
            addTask(entityManager, newTask);
            System.out.println("New Task Added: " + taskDescription);
            request.setAttribute("New Task", newTask);
        }
//        response.sendRedirect(request.getContextPath() + "/addTask");
    }
    // Function that will add tasks to our MySql Database, through entityManager functions
    private void addTask(EntityManager entityManager, Task task) {
        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.getTransaction().commit();
        System.out.println("Task Added!");

        long taskId = task.getTaskId();
        System.out.println("Task Id: " + taskId);
    }
    // Finishes adding to database
    @Override
    public void destroy() {
        factory.close();
    }
}
