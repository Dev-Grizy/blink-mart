package com.blinkmart.inventory_service.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LockInventoryResponse {
    private String status;
    private Integer remainingQty;
}