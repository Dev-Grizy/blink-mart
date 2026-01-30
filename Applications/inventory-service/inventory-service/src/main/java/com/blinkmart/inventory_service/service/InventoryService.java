package com.blinkmart.inventory_service.service;

import com.blinkmart.inventory_service.model.dto.LockInventoryRequest;
import com.blinkmart.inventory_service.model.dto.LockInventoryResponse;

public interface InventoryService {

    LockInventoryResponse lockInventory(LockInventoryRequest request);

    void releaseInventory(Long storeId, String productId, String checkoutId);

    void confirmInventory(Long storeId, String productId, Integer quantity);
}
