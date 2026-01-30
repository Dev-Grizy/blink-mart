package com.blinkmart.inventory_service.controller;

import com.blinkmart.inventory_service.model.dto.LockInventoryRequest;
import com.blinkmart.inventory_service.model.dto.LockInventoryResponse;
import com.blinkmart.inventory_service.model.dto.ReleaseInventoryRequest;
import com.blinkmart.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/lock")
    public LockInventoryResponse lock(@RequestBody LockInventoryRequest request) {
        return inventoryService.lockInventory(request);
    }

    @PostMapping("/release")
    public void release(@RequestBody ReleaseInventoryRequest request) {
        inventoryService.releaseInventory(
                request.getStoreId(),
                request.getProductId(),
                request.getCheckoutId()
        );
    }

    @PostMapping("/confirm")
    public void confirm(
            @RequestParam Long storeId,
            @RequestParam String productId,
            @RequestParam Integer quantity) {

        inventoryService.confirmInventory(storeId, productId, quantity);
    }
}
