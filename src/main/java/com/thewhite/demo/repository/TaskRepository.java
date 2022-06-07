package com.thewhite.demo.repository;

import com.thewhite.demo.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskRepository {

    public Task getById(Long id) {
        throw new RuntimeException("Not implemented!");
    }

    public Task save(Task task) {
        throw new RuntimeException("Not implemented!");
    }
}
