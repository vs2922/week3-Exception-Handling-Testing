package com.pharmainventory.inventory.service;

import com.pharmainventory.inventory.dto.*;
import com.pharmainventory.inventory.exception.ResourceNotFoundException;
import com.pharmainventory.inventory.mapper.MedicineMapper;
import com.pharmainventory.inventory.model.Medicine;
import com.pharmainventory.inventory.repository.MedicineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {
    private final MedicineRepository medicineRepo;

    public InventoryServiceImpl(MedicineRepository medicineRepo) {
        this.medicineRepo = medicineRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicineDTO> listAllMedicines() {
        return medicineRepo.findAll().stream()
            .map(MedicineMapper.INSTANCE::toDto)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MedicineDTO getMedicine(Long id) {
        Medicine m = medicineRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Medicine", id));
        return MedicineMapper.INSTANCE.toDto(m);
    }

    @Override
    public MedicineDTO createMedicine(MedicineDTO dto) {
        Medicine m = MedicineMapper.INSTANCE.toEntity(dto);
        Medicine saved = medicineRepo.save(m);
        return MedicineMapper.INSTANCE.toDto(saved);
    }

    @Override
    public MedicineDTO updateMedicine(Long id, MedicineDTO dto) {
        Medicine existing = medicineRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Medicine", id));
        existing.setName(dto.getName());
        existing.setManufacturer(dto.getManufacturer());
        existing.setExpiryDate(dto.getExpiryDate());
        existing.setCondition(dto.getCondition());
        return MedicineMapper.INSTANCE.toDto(medicineRepo.save(existing));
    }

    @Override
    public void deleteMedicine(Long id) {
        medicineRepo.delete(getMedicine(id).toEntity());
    }

    @Override
    public List<MedicineDTO> listExpiredMedicines() {
        return medicineRepo.findByExpiryDateBefore(LocalDate.now()).stream()
            .map(MedicineMapper.INSTANCE::toDto)
            .collect(Collectors.toList());
    }
}