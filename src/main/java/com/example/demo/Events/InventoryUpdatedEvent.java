package com.example.demo.Events;

import org.springframework.context.ApplicationEvent;

public class InventoryUpdatedEvent extends ApplicationEvent {
    private final Long productId;
    private final int quantityChange;

    public InventoryUpdatedEvent(Object source, Long productId, int quantityChange) {
        super(source);
        this.productId = productId;
        this.quantityChange = quantityChange;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantityChange() {
        return quantityChange;
    }
}
