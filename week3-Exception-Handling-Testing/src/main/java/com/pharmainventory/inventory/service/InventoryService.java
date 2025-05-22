package com.pharmainventory.inventory.service;

import com.pharmainventory.inventory.dto.*;
import java.util.List;

public interface InventoryService {
    List<MedicineDTO> listAllMedicines();
    MedicineDTO getMedicine(Long id);
    MedicineDTO createMedicine(MedicineDTO dto);
    MedicineDTO updateMedicine(Long id, MedicineDTO dto);
    void deleteMedicine(Long id);
    List<MedicineDTO> listExpiredMedicines();
    // similarly for Pallets and Tasks...
}