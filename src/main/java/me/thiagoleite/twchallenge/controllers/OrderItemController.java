package me.thiagoleite.twchallenge.controllers;

import jakarta.validation.Valid;
import me.thiagoleite.twchallenge.model.entities.Order;
import me.thiagoleite.twchallenge.model.entities.OrderItem;
import me.thiagoleite.twchallenge.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController implements ApiController<OrderItem> {

    @Autowired
    private OrderItemService service;

    @GetMapping
    public Iterable<OrderItem> getAll() {
        return service.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderItem> getById(@PathVariable Long id) {

        Optional<OrderItem> item = service.findById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public @ResponseBody ResponseEntity<OrderItem> create(@RequestBody @Valid OrderItem data) {
        OrderItem item = service.save(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<OrderItem> update(@PathVariable Long id, @RequestBody @Valid OrderItem data) {
        try {
            OrderItem item = service.update(id, data);
            return ResponseEntity.ok(item);
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
