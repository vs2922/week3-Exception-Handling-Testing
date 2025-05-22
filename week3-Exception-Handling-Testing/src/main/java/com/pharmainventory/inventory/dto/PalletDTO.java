package com.pharmainventory.inventory.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PalletDTO {
    private Long id;
    private List<Long> medicineIds;
    private String priority;
    private boolean processed;
}
