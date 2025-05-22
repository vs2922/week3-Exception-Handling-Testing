package com.pharmainventory.inventory.api;

import com.pharmainventory.inventory.controller.MedicineController;
import com.pharmainventory.inventory.dto.MedicineDTO;
import com.pharmainventory.inventory.service.InventoryService;
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

@WebMvcTest(MedicineController.class)
class MedicineControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private InventoryService svc;

    @BeforeEach
    void setup() {
        MedicineDTO dto = new MedicineDTO(1L, "Test", "Mfr", null, null);
        when(svc.listAllMedicines()).thenReturn(Collections.singletonList(dto));
    }

    @Test
    void testList() throws Exception {
        mockMvc.perform(get("/api/medicines"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Test"));
        verify(svc, times(1)).listAllMedicines();
    }
}