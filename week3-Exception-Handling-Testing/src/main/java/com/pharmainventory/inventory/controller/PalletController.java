package com.pharmainventory.inventory.controller;

import com.pharmainventory.inventory.dto.PalletDTO;
import com.pharmainventory.inventory.service.PalletService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pallets")
public class PalletController {
    private final PalletService svc;

    public PalletController(PalletService svc) {
        this.svc = svc;
    }

    @GetMapping
    public List<PalletDTO> list() {
        return svc.listAllPallets();
    }

    @GetMapping("/{id}")
    public PalletDTO get(@PathVariable Long id) {
        return svc.getPallet(id);
    }

    @GetMapping("/priority/{prio}")
    public List<PalletDTO> byPriority(@PathVariable("prio") String prio) {
        return svc.listPalletsByPriority(prio.toUpperCase());
    }

    @PostMapping
    public PalletDTO create(@Valid @RequestBody PalletDTO dto) {
        return svc.createPallet(dto);
    }

    @PutMapping("/{id}")
    public PalletDTO update(@PathVariable Long id, @Valid @RequestBody PalletDTO dto) {
        return svc.updatePallet(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        svc.deletePallet(id);
    }
}
