package me.thiagoleite.twchallenge.controllers;

import jakarta.validation.Valid;
import me.thiagoleite.twchallenge.model.entities.OrderItem;
import me.thiagoleite.twchallenge.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Optional<OrderItem> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public @ResponseBody OrderItem create(@RequestBody @Valid OrderItem orderItem) {
        return service.save(orderItem);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody OrderItem update(@PathVariable Long id, @RequestBody @Valid OrderItem data) {
        return service.update(id, data);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    public void remove(@PathVariable Long id) {
        service.deleteById(id);
    }
}
