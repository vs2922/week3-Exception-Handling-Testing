package com.pharmainventory.inventory.controller;

import com.pharmainventory.inventory.dto.TaskDTO;
import com.pharmainventory.inventory.service.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService svc;

    public TaskController(TaskService svc) {
        this.svc = svc;
    }

    @GetMapping
    public List<TaskDTO> list() {
        return svc.listAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDTO get(@PathVariable Long id) {
        return svc.getTask(id);
    }

    @PostMapping
    public TaskDTO create(@Valid @RequestBody TaskDTO dto) {
        return svc.createTask(dto);
    }

    @PutMapping("/{id}")
    public TaskDTO update(@PathVariable Long id, @Valid @RequestBody TaskDTO dto) {
        return svc.updateTask(id, dto);
    }

    @PutMapping("/{id}/complete")
    public TaskDTO complete(@PathVariable Long id) {
        return svc.completeTask(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        svc.deleteTask(id);
    }
}
