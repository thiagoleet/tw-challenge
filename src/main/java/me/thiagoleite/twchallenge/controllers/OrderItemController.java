package me.thiagoleite.twchallenge.controllers;

import jakarta.validation.Valid;
import me.thiagoleite.twchallenge.model.entities.OrderItem;
import me.thiagoleite.twchallenge.model.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemRepository repository;

    @GetMapping
    public Iterable<OrderItem> getAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<OrderItem> getById(@PathVariable long id) {
        return repository.findById(id);
    }

    @PostMapping
    public @ResponseBody OrderItem create(@RequestBody @Valid OrderItem orderItem) {
        repository.save(orderItem);
        return orderItem;
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody OrderItem update(@PathVariable long id, @RequestBody @Valid OrderItem orderItem) {
        Optional<OrderItem> exists = repository.findById(id);

        if(exists.isPresent()) {
            orderItem.setId(id);
            repository.save(orderItem);

            return  orderItem;
        }

        return  null;
    }

    @DeleteMapping(path = "/{id}")
    public void remove(@PathVariable long id) {
        repository.deleteById(id);
    }
}
