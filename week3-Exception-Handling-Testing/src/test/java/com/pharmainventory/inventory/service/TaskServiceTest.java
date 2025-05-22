package com.pharmainventory.inventory.service;

import com.pharmainventory.inventory.dto.TaskDTO;
import com.pharmainventory.inventory.model.Task;
import com.pharmainventory.inventory.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {
    private TaskRepository repo;
    private TaskService svc;

    @BeforeEach
    void setup() {
        repo = mock(TaskRepository.class);
        svc = new TaskServiceImpl(repo);
    }

    @Test
    void completeTask_setsCompleted() {
        Task t = new Task(1L, "desc", "user", false);
        when(repo.findById(1L)).thenReturn(Optional.of(t));
        TaskDTO dto = svc.completeTask(1L);
        assertTrue(dto.isCompleted());
        verify(repo).save(any(Task.class));
    }
}
