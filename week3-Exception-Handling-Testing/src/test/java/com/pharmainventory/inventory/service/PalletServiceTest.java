package com.pharmainventory.inventory.service;

import com.pharmainventory.inventory.dto.PalletDTO;
import com.pharmainventory.inventory.model.Pallet;
import com.pharmainventory.inventory.repository.PalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PalletServiceTest {
    private PalletRepository repo;
    private PalletService svc;

    @BeforeEach
    void setup() {
        repo = mock(PalletRepository.class);
        svc = new PalletServiceImpl(repo);
    }

    @Test
    void listPalletsByPriority_returnsDtos() {
        Pallet p = new Pallet(1L, null, "URGENT", false);
        when(repo.findByPriority("URGENT")).thenReturn(Arrays.asList(p));
        List<PalletDTO> result = svc.listPalletsByPriority("URGENT");
        assertEquals(1, result.size());
        assertEquals("URGENT", result.get(0).getPriority());
    }
}
