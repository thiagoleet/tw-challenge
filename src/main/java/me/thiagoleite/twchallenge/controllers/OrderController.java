package me.thiagoleite.twchallenge.controllers;

import jakarta.validation.Valid;
import me.thiagoleite.twchallenge.model.entities.Order;
import me.thiagoleite.twchallenge.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController implements ApiController<Order> {

    @Autowired
    private OrderService service;

    @GetMapping
    public Iterable<Order> getAll() {
        return service.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        Optional<Order> order = service.findById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public @ResponseBody ResponseEntity<Order> create(@RequestBody @Valid Order data) {
        Order createdOrder = service.save(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<Order> update(@PathVariable Long id, @RequestBody @Valid Order data) {
        try {
            Order order = service.update(id, data);
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
