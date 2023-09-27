package me.thiagoleite.twchallenge.controllers;

import jakarta.validation.Valid;
import me.thiagoleite.twchallenge.model.entities.Order;
import me.thiagoleite.twchallenge.model.entities.Product;
import me.thiagoleite.twchallenge.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController implements ApiController<Product> {

    @Autowired
    private ProductService service;

    @GetMapping
    public Iterable<Product> getAll() {
        return service.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {

        Optional<Product> product = service.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Product> create(@RequestBody @Valid Product data) {
        Product product = service.save(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<Product> update(@PathVariable Long id, @RequestBody @Valid Product data) {
        try {
            Product product = service.update(id, data);
            return ResponseEntity.ok(product);
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
