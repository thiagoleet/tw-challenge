package me.thiagoleite.twchallenge.services;

import me.thiagoleite.twchallenge.model.entities.OrderItem;
import me.thiagoleite.twchallenge.model.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository repository;

    public Iterable<OrderItem> findAll() {
        return repository.findAll();

    }

    public Optional<OrderItem> findById(Long id) {
        return repository.findById(id);
    }

    public OrderItem save(OrderItem orderItem) {
        return repository.save(orderItem);
    }

    public OrderItem update(OrderItem orderItem, Long id) {
        Optional<OrderItem> exists = findById(id);

        if (exists.isPresent()) {
            orderItem.setId(id);
            return save(orderItem);
        }

        return null;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
