package com.pharmainventory.inventory.controller;

import com.pharmainventory.inventory.dto.TaskDTO;
import com.pharmainventory.inventory.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private TaskService svc;

    @BeforeEach
    void setup() {
        TaskDTO dto = new TaskDTO(1L, "desc", "user", false);
        when(svc.listAllTasks()).thenReturn(Collections.singletonList(dto));
    }

    @Test
    void testList() throws Exception {
        mockMvc.perform(get("/api/tasks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].description").value("desc"));
        verify(svc).listAllTasks();
    }
}
