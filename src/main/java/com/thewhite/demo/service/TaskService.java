package com.thewhite.demo.service;

import com.thewhite.demo.model.Task;
import com.thewhite.demo.model.User;
import com.thewhite.demo.repository.TaskRepository;
import com.thewhite.demo.util.Guard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.thewhite.demo.model.TaskStatus.DONE;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public Task create(CreateTaskArgument argument) {
        return repository.save(
                Task.builder()
                    .title(argument.getTitle())
                    .content(argument.getContent())
                    .deadline(argument.getDeadline())
                    .urgency(argument.isUrgency())
                    .executor(argument.getExecutor())
                    .checkList(argument.getCheckList())
                    .build());
    }

    public void done(Long taskId) {
        Task task = repository.getById(taskId);

        User executor = task.getExecutor();

        Guard.check(task.isCheckListComplete() || executor.isChief(),
                    "Only chief can done task without complete check list!");

        task.setStatus(DONE);

        repository.save(task);
    }
}