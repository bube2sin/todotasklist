package com.example.todotasklist3.repository;

import com.example.todotasklist3.model.Task;
import com.example.todotasklist3.repository.TaskRepositoryCustom;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepositoryCustomImpl implements TaskRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public long updateTaskStatusOnly(Task task) {
        Query query = new Query(Criteria.where("_id").is(task.get_id()));
        Update update = new Update();
        update.set("status", task.getStatus());
        UpdateResult result = mongoTemplate.updateFirst(query, update, Task.class);
        if(result!=null)
            return result.getMatchedCount();
        else
            return 0;
    }
}
