package com.example.todotasklist3.controller;

import com.example.todotasklist3.exception.StatusException;
import com.example.todotasklist3.exception.SubjectException;
import com.example.todotasklist3.exception.TaskNotFoundException;
import com.example.todotasklist3.model.Task;
import com.example.todotasklist3.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    public static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;


    //getAll
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> list = taskService.getAllTasks();
        return new ResponseEntity<List<Task>>(list, HttpStatus.OK);
    }

    //getById
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTask(@PathVariable("id") String id) {
        Task task = taskService.getTaskById(id);
        if (task==null) {
            throw new TaskNotFoundException(id);
        }
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    //add
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        //check subject
        if(isValidSubject(task.getSubject())) {
            throw new SubjectException();
        }
        //check status
        if(isValidStatus(task.getStatus())) {
            throw new StatusException();
        }
        boolean addSucceed = taskService.addTask(task);
        if(!addSucceed)
            return new ResponseEntity<String>("Cannot Add a new Task", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Task>(task, HttpStatus.CREATED);
    }

    //update - existing task
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateExistingTask(@PathVariable("id") String id, @RequestBody Task task) {
        logger.info("Update exising task");
        // check subject
        if(isValidSubject(task.getSubject())) {
            throw new SubjectException();
        }
        // check status
        if(isValidStatus(task.getStatus())) {
            throw new StatusException();
        }
        task.set_id(id);
        boolean updateSucceed = taskService.updateExitingTask(task);
        if(!updateSucceed)
            return new ResponseEntity<String>("Cannot update ID : '"+ id + "'", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Task>(task, HttpStatus.CREATED);
    }

    //patch - status
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateTaskStatus(@PathVariable("id") String id, @RequestBody Task task) {
        logger.info("Update partial status task");
        if(isValidStatus(task.getStatus())) {
            throw new StatusException();
        }
        task.set_id(id);
        boolean updateSucceed = taskService.updateStatus(task);
        if(!updateSucceed)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Task>(task, HttpStatus.CREATED);
    }

    //delete
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeTask(@PathVariable String id) {
        logger.info("Delete task");
        Task task = taskService.getTaskById(id);
        if (task==null) {
            throw new TaskNotFoundException(id);
        }
        taskService.deleteTask(id);
        return new ResponseEntity<>("Delete ID : '" + id + "' Succeed", HttpStatus.OK);
    }

    /**General**/
    private boolean isValidSubject(String subject) {
        return subject == null || subject.length()<=0;
    }

    private boolean isValidStatus(String status) {
        List<String> myList = Arrays.asList("Pending","Done");
        if(status == null || status.length() <= 0)
            return true;
        if(!myList.contains(status))
            return true;
        return false;
    }
}
