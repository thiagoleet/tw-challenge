package me.thiagoleite.twchallenge.controllers;

import jakarta.validation.Valid;
import me.thiagoleite.twchallenge.model.entities.Order;
import me.thiagoleite.twchallenge.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public Iterable<Order> getAll() {
        return service.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Order> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public @ResponseBody Order create(@RequestBody @Valid Order order) {
        return service.save(order);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody Order update(@PathVariable Long id, @RequestBody @Valid Order order) {
        return service.update(order, id);
    }

    @DeleteMapping(path = "/{id}")
    public void remove(@PathVariable Long id) {
        service.deleteById(id);
    }
}
