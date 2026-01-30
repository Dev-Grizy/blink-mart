package com.blinkmart.inventory_service.service.impl;

import com.blinkmart.inventory_service.model.dto.LockInventoryRequest;
import com.blinkmart.inventory_service.model.dto.LockInventoryResponse;
import com.blinkmart.inventory_service.model.entity.Inventory;
import com.blinkmart.inventory_service.repository.InventoryRepository;
import com.blinkmart.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final StringRedisTemplate redisTemplate;

    private static final Duration LOCK_TTL = Duration.ofMinutes(5);

    @Override
    public LockInventoryResponse lockInventory(LockInventoryRequest request) {

        Inventory inventory = inventoryRepository
                .findByStoreIdAndProductId(request.getStoreId(), request.getProductId())
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        if (inventory.getAvailableQty() < request.getQuantity()) {
            return LockInventoryResponse.builder()
                    .status("OUT_OF_STOCK")
                    .remainingQty(inventory.getAvailableQty())
                    .build();
        }

        String redisKey = buildLockKey(
                request.getStoreId(),
                request.getProductId(),
                request.getCheckoutId()
        );

        redisTemplate.opsForValue()
                .set(redisKey, request.getQuantity().toString(), LOCK_TTL);

        return LockInventoryResponse.builder()
                .status("LOCKED")
                .remainingQty(inventory.getAvailableQty() - request.getQuantity())
                .build();
    }

    @Override
    public void releaseInventory(Long storeId, String productId, String checkoutId) {
        redisTemplate.delete(buildLockKey(storeId, productId, checkoutId));
    }

    @Override
    public void confirmInventory(Long storeId, String productId, Integer quantity) {

        Inventory inventory = inventoryRepository
                .findByStoreIdAndProductId(storeId, productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        inventory.setAvailableQty(inventory.getAvailableQty() - quantity);
        inventory.setReservedQty(inventory.getReservedQty() + quantity);

        inventoryRepository.save(inventory);
    }

    private String buildLockKey(Long storeId, String productId, String checkoutId) {
        return "INV_LOCK:" + storeId + ":" + productId + ":" + checkoutId;
    }
}