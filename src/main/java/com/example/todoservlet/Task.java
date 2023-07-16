package com.example.todoservlet;
import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.servlet.http.HttpServletResponse;
@Entity
@Table(name = "tasks", schema = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TaskId")
    // Private variables, Hibernate framework requires mostly private variables and functions
    private Long id;
    @Basic
    @Column(name = "TaskDescription")
    private String description;
    @Basic
    @Column(name = "Date")
    private LocalDate date;
    // Empty constructor Hibernate will use to add new entries
    public Task() {}
    // This is our task object constructor / Auxillary Constructor
    public Task(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }
    // Task-getter
    public long getTaskId() {
        return id;
    }

    public void setTaskId(Long id) {
        this.id = id;
    }

    public String getTaskDescription() {
        return description;
    }

    public void setTaskDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @PrePersist
    public void prePersist() {
        date = LocalDate.now();
    }
}
