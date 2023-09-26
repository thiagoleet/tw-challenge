package me.thiagoleite.twchallenge.controllers;

import jakarta.validation.Valid;
import me.thiagoleite.twchallenge.model.entities.Order;
import me.thiagoleite.twchallenge.model.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @GetMapping
    public Iterable<Order> getAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Order> getById(@PathVariable long id) {
        return repository.findById(id);
    }

    @PostMapping
    public @ResponseBody Order create(@RequestBody @Valid Order order) {
        repository.save(order);
        return order;
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody Order update(@PathVariable long id, @RequestBody @Valid Order order) {
        Optional<Order> exists = repository.findById(id);

        if(exists.isPresent()) {
            order.setId(id);
            repository.save(order);

            return  order;
        }

        return  null;
    }

    @DeleteMapping(path = "/{id}")
    public void remove(@PathVariable long id) {
        repository.deleteById(id);
    }
}
