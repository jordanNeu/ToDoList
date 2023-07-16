<%@ page import="com.example.todoservlet.Task" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - To Do WEB UI</title>
</head>
<%--Form that will allow the user to enter a new task, '/addTask' connects to the addTaskServlet via tags--%>
<body>
<div class="box">
    <div class="container">
        <h1>To Do List - Web UI</h1>
        <br/>
        <% if (request.getAttribute("tasks") != null && !((List<Task>) request.getAttribute("tasks")).isEmpty()) { %>
        <h2>Tasks:</h2>
        <div class = "taskList">
        <ul>
            <% for (Task task : (List<Task>) request.getAttribute("tasks")) { %>
            <li><%= task.getTaskId() %>: <%= task.getTaskDescription() %> - <%= task.getDate() %></li>
            <% } %>
        </ul>
        </div>
        <a href="index.jsp">Go Home</a>
    </div>
        <% } else { %>
<%--        Form action talks to the servlet to add a new task to the database--%>
        <form action="addTask" method="post">
            <div class ="addTaskDiv">
                <span class="task"><label for="taskDescription">Enter New Task:</label></span>
                <span class="formInput"><input type="text" id="taskDescription" name="taskDescription"></span>
                <div class="button1"><button type="submit">Submit</button></div>
                <div class="button2"><button type="reset">Clear</button></div>

            </div>
        </form>
    <a href="${pageContext.request.contextPath}list">View Tasks</a>
        <% } %>
</div>
</body>
</html>
