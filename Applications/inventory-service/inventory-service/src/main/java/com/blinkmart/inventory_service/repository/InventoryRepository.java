package com.blinkmart.inventory_service.repository;

import com.blinkmart.inventory_service.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByStoreIdAndProductId(Long storeId, String productId);
}