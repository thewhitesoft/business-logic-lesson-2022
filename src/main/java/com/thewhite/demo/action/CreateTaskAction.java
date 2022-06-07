package com.thewhite.demo.action;

import com.thewhite.demo.model.Task;
import com.thewhite.demo.model.User;
import com.thewhite.demo.security.AuthService;
import com.thewhite.demo.service.CreateTaskArgument;
import com.thewhite.demo.service.TaskService;
import com.thewhite.demo.util.Guard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateTaskAction {

    private final AuthService authService;
    private final TaskService taskService;

    public Task execute(CreateTaskActionArgument argument) {
        User executor = authService.getCurrentUser();

        Guard.check(!argument.isUrgency() || executor.isChief(),
                    "Only chief can create urgent task!");

        return taskService.create(CreateTaskArgument.builder()
                                                    .title(argument.getTitle())
                                                    .content(argument.getContent())
                                                    .deadline(argument.getDeadline())
                                                    .urgency(argument.isUrgency())
                                                    .executor(executor)
                                                    .checkList(argument.getCheckList())
                                                    .build());
    }
}