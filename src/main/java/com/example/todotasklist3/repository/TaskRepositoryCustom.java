package com.example.todotasklist3.repository;

import com.example.todotasklist3.model.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositoryCustom {
    long updateTaskStatusOnly(Task task);
}
