package me.thiagoleite.twchallenge.services;

import me.thiagoleite.twchallenge.model.entities.Order;
import me.thiagoleite.twchallenge.model.entities.Product;
import me.thiagoleite.twchallenge.model.repositories.OrderItemRepository;
import me.thiagoleite.twchallenge.model.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService implements ApiService<Order> {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderItemRepository itemRepository;

    public Iterable<Order> findAll() {
        return repository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    public Order save(Order data) {
        return repository.save(data);
    }

    public Order update(Long id, Order updatedData) {
        Optional<Order> existingOrder = repository.findById(id);
        if (existingOrder.isPresent()) {
            updatedData.setId(id);
            return repository.save(updatedData);
        } else {
            throw new IllegalArgumentException("Order not found with id: " + id);
        }

    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
