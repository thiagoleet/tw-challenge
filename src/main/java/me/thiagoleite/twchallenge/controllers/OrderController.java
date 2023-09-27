package me.thiagoleite.twchallenge.controllers;

import jakarta.validation.Valid;
import me.thiagoleite.twchallenge.model.entities.Order;
import me.thiagoleite.twchallenge.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Optional<Order> getById(@PathVariable Long id) {
        return service.findById(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public @ResponseBody Order create(@RequestBody @Valid Order data) {
        return service.save(data);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody Order update(@PathVariable Long id, @RequestBody @Valid Order data) {
        return service.update(id, data);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    public void remove(@PathVariable Long id) {
        service.deleteById(id);
    }
}
