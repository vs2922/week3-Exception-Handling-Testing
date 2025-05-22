package com.pharmainventory.inventory.service;

import com.pharmainventory.inventory.dto.PalletDTO;
import com.pharmainventory.inventory.exception.ResourceNotFoundException;
import com.pharmainventory.inventory.mapper.PalletMapper;
import com.pharmainventory.inventory.model.Pallet;
import com.pharmainventory.inventory.repository.PalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PalletServiceImpl implements PalletService {
    private final PalletRepository repo;

    public PalletServiceImpl(PalletRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PalletDTO> listAllPallets() {
        return repo.findAll().stream()
                   .map(PalletMapper.INSTANCE::toDto)
                   .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PalletDTO getPallet(Long id) {
        Pallet p = repo.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("Pallet", id));
        return PalletMapper.INSTANCE.toDto(p);
    }

    @Override
    public PalletDTO createPallet(PalletDTO dto) {
        Pallet p = PalletMapper.INSTANCE.toEntity(dto);
        Pallet saved = repo.save(p);
        return PalletMapper.INSTANCE.toDto(saved);
    }

    @Override
    public PalletDTO updatePallet(Long id, PalletDTO dto) {
        Pallet existing = repo.findById(id)
                              .orElseThrow(() -> new ResourceNotFoundException("Pallet", id));
        existing.setProcessed(dto.isProcessed());
        existing.setPriority(dto.getPriority());
        // medicineIds mapping omitted for brevity
        return PalletMapper.INSTANCE.toDto(repo.save(existing));
    }

    @Override
    public void deletePallet(Long id) {
        Pallet p = repo.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("Pallet", id));
        repo.delete(p);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PalletDTO> listPalletsByPriority(String priority) {
        return repo.findByPriority(priority).stream()
                   .map(PalletMapper.INSTANCE::toDto)
                   .collect(Collectors.toList());
    }
}
