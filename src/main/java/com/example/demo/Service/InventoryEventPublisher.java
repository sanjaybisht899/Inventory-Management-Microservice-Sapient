package com.example.demo.Service;

import com.example.demo.Events.InventoryUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class InventoryEventPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publishInventoryUpdate(Long productId, int quantityChange) {
        InventoryUpdatedEvent event = new InventoryUpdatedEvent(this, productId, quantityChange);
        publisher.publishEvent(event);
    }
}
