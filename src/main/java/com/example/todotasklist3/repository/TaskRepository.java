package com.example.todotasklist3.repository;

import com.example.todotasklist3.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task,String>,TaskRepositoryCustom {
    Task findBy_id(String id);
}
