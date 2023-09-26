package me.thiagoleite.twchallenge.controllers;

import jakarta.validation.Valid;
import me.thiagoleite.twchallenge.model.entities.Product;
import me.thiagoleite.twchallenge.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public Iterable<Product> getAll() {
        return service.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Product> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public @ResponseBody Product create(@RequestBody @Valid Product product) {
        return service.save(product);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody Product update(@PathVariable long id, @RequestBody @Valid Product product) {
        return service.update(product, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    public void remove(@PathVariable long id) {
        service.deleteById(id);
    }
}
