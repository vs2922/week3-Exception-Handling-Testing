package com.pharmainventory.inventory.controller;

import com.pharmainventory.inventory.dto.MedicineDTO;
import com.pharmainventory.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {
    private final InventoryService svc;

    public MedicineController(InventoryService svc) {
        this.svc = svc;
    }

    @GetMapping
    public List<MedicineDTO> list() {
        return svc.listAllMedicines();
    }

    @GetMapping("/{id}")
    public MedicineDTO get(@PathVariable Long id) {
        return svc.getMedicine(id);
    }

    @PostMapping
    public MedicineDTO create(@Valid @RequestBody MedicineDTO dto) {
        return svc.createMedicine(dto);
    }

    @PutMapping("/{id}")
    public MedicineDTO update(@PathVariable Long id, @Valid @RequestBody MedicineDTO dto) {
        return svc.updateMedicine(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        svc.deleteMedicine(id);
    }
}