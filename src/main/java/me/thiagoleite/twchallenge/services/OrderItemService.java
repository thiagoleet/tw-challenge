package me.thiagoleite.twchallenge.services;

import me.thiagoleite.twchallenge.model.entities.Order;
import me.thiagoleite.twchallenge.model.entities.OrderItem;
import me.thiagoleite.twchallenge.model.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService implements ApiService<OrderItem> {
    @Autowired
    private OrderItemRepository repository;

    public Iterable<OrderItem> findAll() {
        return repository.findAll();

    }

    public Optional<OrderItem> findById(Long id) {
        return repository.findById(id);
    }

    public OrderItem save(OrderItem data) {
        return repository.save(data);
    }


    public OrderItem update(Long id, OrderItem updatedData) {
        Optional<OrderItem> existingOrderItem = repository.findById(id);
        if (existingOrderItem.isPresent()) {
            updatedData.setId(id);
            return repository.save(updatedData);
        } else {
            throw new IllegalArgumentException("Order Item not found with id: " + id);
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
