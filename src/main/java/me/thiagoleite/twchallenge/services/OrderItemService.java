package me.thiagoleite.twchallenge.services;

import me.thiagoleite.twchallenge.model.entities.Order;
import me.thiagoleite.twchallenge.model.entities.OrderItem;
import me.thiagoleite.twchallenge.model.repositories.OrderItemRepository;
import me.thiagoleite.twchallenge.model.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class OrderItemService implements ApiService<OrderItem> {
    @Autowired
    private OrderItemRepository repository;

    @Autowired
    private OrderRepository orderRepository;

    public Iterable<OrderItem> findAll() {
        return repository.findAll();

    }

    public Optional<OrderItem> findById(Long id) {
        return repository.findById(id);
    }

    public OrderItem create(OrderItem data) {
        return repository.save(data);
    }


    public OrderItem update(Long id, OrderItem updatedData) {
        Optional<OrderItem> existingOrderItem = repository.findById(id);
        if (existingOrderItem.isPresent()) {
            Optional<Order> order = orderRepository.findById(updatedData.getOrder().getId());
            if (order.isPresent()) {
                updatedData.setOrder(order.get());
                updatedData.setId(id);
                return repository.save(updatedData);
            } else {
                throw new IllegalArgumentException("Order not found");
            }
        } else {
            throw new IllegalArgumentException("Order Item not found with id: " + id);
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
