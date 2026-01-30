package com.blinkmart.inventory_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "inventory",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"storeId", "productId"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    private Long storeId;
    private String productId;

    private Integer availableQty;
    private Integer reservedQty;
}
