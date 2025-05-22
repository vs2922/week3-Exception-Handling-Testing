package com.pharmainventory.inventory.service;

import com.pharmainventory.inventory.dto.PalletDTO;
import java.util.List;

public interface PalletService {
    List<PalletDTO> listAllPallets();
    PalletDTO getPallet(Long id);
    PalletDTO createPallet(PalletDTO dto);
    PalletDTO updatePallet(Long id, PalletDTO dto);
    void deletePallet(Long id);
    List<PalletDTO> listPalletsByPriority(String priority);
}
