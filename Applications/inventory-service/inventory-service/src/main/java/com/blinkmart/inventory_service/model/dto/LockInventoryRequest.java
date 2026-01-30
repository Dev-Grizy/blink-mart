package com.blinkmart.inventory_service.model.dto;

import lombok.Data;

@Data
public class LockInventoryRequest {
    private Long storeId;
    private String productId;
    private Integer quantity;
    private String checkoutId;
}
