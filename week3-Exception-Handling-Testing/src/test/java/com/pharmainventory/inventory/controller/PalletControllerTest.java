package com.pharmainventory.inventory.controller;

import com.pharmainventory.inventory.dto.PalletDTO;
import com.pharmainventory.inventory.service.PalletService;
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

@WebMvcTest(PalletController.class)
class PalletControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private PalletService svc;

    @BeforeEach
    void setup() {
        PalletDTO dto = new PalletDTO(1L, Collections.emptyList(), "STANDARD", false);
        when(svc.listAllPallets()).thenReturn(Collections.singletonList(dto));
    }

    @Test
    void testList() throws Exception {
        mockMvc.perform(get("/api/pallets"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].priority").value("STANDARD"));
        verify(svc).listAllPallets();
    }
}
