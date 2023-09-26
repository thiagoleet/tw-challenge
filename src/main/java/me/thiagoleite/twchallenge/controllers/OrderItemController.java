package me.thiagoleite.twchallenge.controllers;

import jakarta.validation.Valid;
import me.thiagoleite.twchallenge.model.entities.OrderItem;
import me.thiagoleite.twchallenge.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService service;

    @GetMapping
    public Iterable<OrderItem> getAll() {
        return service.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<OrderItem> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public @ResponseBody OrderItem create(@RequestBody @Valid OrderItem orderItem) {
        return service.save(orderItem);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody OrderItem update(@PathVariable Long id, @RequestBody @Valid OrderItem orderItem) {
        return service.update(orderItem, id);
    }

    @DeleteMapping(path = "/{id}")
    public void remove(@PathVariable Long id) {
        service.deleteById(id);
    }
}
