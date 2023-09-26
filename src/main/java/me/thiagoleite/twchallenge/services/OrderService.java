package me.thiagoleite.twchallenge.services;

import me.thiagoleite.twchallenge.model.entities.Order;
import me.thiagoleite.twchallenge.model.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Iterable<Order> findAll() {
        return repository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    public Order save(Order order) {
        return repository.save(order);
    }

    public Order update(Order order, Long id) {
        Optional<Order> exists = findById(id);

        if (exists.isPresent()) {
            order.setId(id);
            return save(order);
        }

        return null;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
