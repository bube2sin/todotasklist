package com.example.todotasklist3.service;

import com.example.todotasklist3.model.Task;
import com.example.todotasklist3.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(String id) {
        return taskRepository.findBy_id(id);
    }

    @Override
    public boolean addTask(Task task) {
        Task added = taskRepository.save(task);
        if(added==null)
            return false;
        return true;
    }

    @Override
    public boolean updateExitingTask(Task task) {
        Task updataed = taskRepository.save(task);
        if(updataed==null)
            return false;
        return true;
    }

    @Override
    public boolean updateStatus(Task task) {
        long updated = taskRepository.updateTaskStatusOnly(task);
        if(updated<=0)
            return false;
        return true;
    }

    @Override
    public void deleteTask(String id) {
        taskRepository.delete(getTaskById(id));
    }


}
