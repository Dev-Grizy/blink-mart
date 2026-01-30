package com.blinkmart.inventory_service.model.dto;

import lombok.Data;

@Data
public class ReleaseInventoryRequest {
    private Long storeId;
    private String productId;
    private String checkoutId;
}
