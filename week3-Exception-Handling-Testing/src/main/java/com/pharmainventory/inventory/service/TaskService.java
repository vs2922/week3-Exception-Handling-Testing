package com.pharmainventory.inventory.service;

import com.pharmainventory.inventory.dto.TaskDTO;
import java.util.List;

public interface TaskService {
    List<TaskDTO> listAllTasks();
    TaskDTO getTask(Long id);
    TaskDTO createTask(TaskDTO dto);
    TaskDTO updateTask(Long id, TaskDTO dto);
    void deleteTask(Long id);
    TaskDTO completeTask(Long id);
}
