package com.pharmainventory.inventory.service;

import com.pharmainventory.inventory.dto.MedicineDTO;
import com.pharmainventory.inventory.model.Medicine;
import com.pharmainventory.inventory.repository.MedicineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryServiceTest {
    private MedicineRepository repo;
    private InventoryServiceImpl svc;

    @BeforeEach
    void setup() {
        repo = mock(MedicineRepository.class);
        svc = new InventoryServiceImpl(repo);
    }

    @Test
    void listExpiredMedicines_returnsDtos() {
        Medicine m = new Medicine(1L, "Test", "Mfr", LocalDate.now().minusDays(1), "NORMAL");
        when(repo.findByExpiryDateBefore(any(LocalDate.class))).thenReturn(Arrays.asList(m));
        List<MedicineDTO> dtos = svc.listExpiredMedicines();
        assertEquals(1, dtos.size());
        assertEquals("Test", dtos.get(0).getName());
    }
}