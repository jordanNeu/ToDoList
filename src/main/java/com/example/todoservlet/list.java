package com.example.todoservlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
// Simple Java To-DO List, using Hibernation, that allows a user to enter a number corresponding to
// the choice they'd like to make, entries will be added to our local database
// Features include: Adding new tasks, deleting existing tasks
// and listing all tasks

// Tag that Coordinates with .jsp file, used in place of web.xml file
@WebServlet(name = "list", value = "/list")
public class list extends HttpServlet {
    private static EntityManagerFactory factory;

    public void init() throws ServletException {
        factory = Persistence.createEntityManagerFactory("tasks");
    }
    // Do-Get runs once accessed by JSP file, not able to get this connected,
    // Designed to list tasks in database
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Do GET FIRED");
        EntityManager entityManager = factory.createEntityManager();
        List<Task> tasks = listTasks(entityManager);
        request.setAttribute("tasks", tasks);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
    // Function to return contents of database, list of tasks
    private List<Task> listTasks(EntityManager entityManager) {
        return entityManager.createQuery("SELECT u FROM Task u", Task.class).getResultList();
    }
    // Finishes adding to database
    @Override
    public void destroy() {
        factory.close();
    }
}
