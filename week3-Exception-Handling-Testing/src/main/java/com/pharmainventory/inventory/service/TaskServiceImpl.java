package com.pharmainventory.inventory.service;

import com.pharmainventory.inventory.dto.TaskDTO;
import com.pharmainventory.inventory.exception.ResourceNotFoundException;
import com.pharmainventory.inventory.mapper.TaskMapper;
import com.pharmainventory.inventory.model.Task;
import com.pharmainventory.inventory.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repo;

    public TaskServiceImpl(TaskRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> listAllTasks() {
        return repo.findAll().stream()
                   .map(TaskMapper.INSTANCE::toDto)
                   .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDTO getTask(Long id) {
        Task t = repo.findById(id)
                     .orElseThrow(() -> new ResourceNotFoundException("Task", id));
        return TaskMapper.INSTANCE.toDto(t);
    }

    @Override
    public TaskDTO createTask(TaskDTO dto) {
        Task t = TaskMapper.INSTANCE.toEntity(dto);
        Task saved = repo.save(t);
        return TaskMapper.INSTANCE.toDto(saved);
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO dto) {
        Task existing = repo.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Task", id));
        existing.setDescription(dto.getDescription());
        existing.setAssignee(dto.getAssignee());
        Task updated = repo.save(existing);
        return TaskMapper.INSTANCE.toDto(updated);
    }

    @Override
    public void deleteTask(Long id) {
        Task t = repo.findById(id)
                     .orElseThrow(() -> new ResourceNotFoundException("Task", id));
        repo.delete(t);
    }

    @Override
    public TaskDTO completeTask(Long id) {
        Task existing = repo.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Task", id));
        existing.setCompleted(true);
        Task updated = repo.save(existing);
        return TaskMapper.INSTANCE.toDto(updated);
    }
}
