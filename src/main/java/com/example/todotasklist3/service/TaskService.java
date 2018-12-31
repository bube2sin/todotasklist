package com.example.todotasklist3.service;

import com.example.todotasklist3.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();
    Task getTaskById(String id);
    boolean addTask(Task task);
    boolean updateExitingTask(Task task);
    boolean updateStatus(Task task);
    void deleteTask(String id);

}
