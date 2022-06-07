package com.thewhite.demo.service;

import com.thewhite.demo.model.Task;
import com.thewhite.demo.model.User;
import com.thewhite.demo.model.UserRole;
import com.thewhite.demo.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.thewhite.demo.model.TaskStatus.DONE;
import static com.thewhite.demo.model.TaskStatus.IN_PROGRESS;
import static com.thewhite.demo.model.UserRole.CHIEF;
import static com.thewhite.demo.model.UserRole.EMPLOYEE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private final TaskRepository repository = mock(TaskRepository.class);
    private final TaskService service = new TaskService(repository);

    @Test
    void doneWhenExecutorIsEmployeeAndCheckListEmpty() {
        // Arrange
        Long taskId = 1L;
        Task task = mockTask(EMPLOYEE, new HashMap<>());

        when(repository.getById(taskId)).thenReturn(task);

        // Act
        service.done(taskId);

        // Assert
        assertThat(task.getStatus()).isEqualTo(DONE);

        verify(repository).save(task);
    }

    @Test
    void doneWhenExecutorIsEmployeeAndCheckListCheckAllCompleted() {
        // Arrange
        Long taskId = 1L;
        Task task = mockTask(EMPLOYEE,
                             new HashMap<String, Boolean>() {{
                                 put("first check", true);
                             }});

        when(repository.getById(taskId)).thenReturn(task);

        // Act
        service.done(taskId);

        // Assert
        assertThat(task.getStatus()).isEqualTo(DONE);

        verify(repository).save(task);
    }

    @Test
    void doneWhenExecutorIsEmployeeAndCheckListHasNotCompleteCheck() {
        // Arrange
        Long taskId = 1L;
        Task task = mockTask(EMPLOYEE,
                             new HashMap<String, Boolean>() {{
                                 put("first check", true);
                                 put("second check", false);
                             }});

        when(repository.getById(taskId)).thenReturn(task);

        // Act
        Throwable exception = assertThrows(RuntimeException.class, () -> service.done(taskId));

        // Assert
        assertEquals("Only chief can done task without complete check list!", exception.getMessage());

        verify(repository, never()).save(task);
    }

    @Test
    void doneWhenExecutorIsChiefAndCheckListHasNotCompleteCheck() {
        // Arrange
        Long taskId = 1L;
        Task task = mockTask(CHIEF,
                             new HashMap<String, Boolean>() {{
                                 put("first check", true);
                                 put("second check", false);
                             }});

        when(repository.getById(taskId)).thenReturn(task);

        // Act
        service.done(taskId);

        // Assert
        assertThat(task.getStatus()).isEqualTo(DONE);

        verify(repository).save(task);
    }

    private Task mockTask(UserRole executorRole, Map<String, Boolean> checkList) {
        User executor = User.builder()
                            .role(executorRole)
                            .build();

        return Task.builder()
                   .executor(executor)
                   .checkList(checkList)
                   .status(IN_PROGRESS)
                   .build();
    }
}