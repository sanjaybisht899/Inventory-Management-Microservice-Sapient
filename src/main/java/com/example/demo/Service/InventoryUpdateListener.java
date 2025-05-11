package com.example.demo.Service;

import com.example.demo.Events.InventoryUpdatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryUpdateListener {

    @EventListener
    public void handleInventoryUpdated(InventoryUpdatedEvent event) {
        System.out.println("Inventory Update Event received for Product ID: " +
                event.getProductId() + ", Quantity Change: " + event.getQuantityChange());

        // Additional logic like logging, alerting, analytics, etc.
    }
}
