package me.thiagoleite.twchallenge.controllers;

import jakarta.validation.Valid;
import me.thiagoleite.twchallenge.model.entities.Product;
import me.thiagoleite.twchallenge.model.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Product> getProductById(@PathVariable long id) {
        return productRepository.findById(id);
    }

    @PostMapping
    public @ResponseBody Product createProduct(@Valid Product product) {
        productRepository.save(product);
        return product;
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody Product updateProduct(@PathVariable long id, @Valid Product product) {
        Optional<Product> exists = productRepository.findById(id);

        if(exists.isPresent()) {
            product.setId(id);
            productRepository.save(product);

            return  product;
        }

        return  null;
    }

    @DeleteMapping(path = "/{id}")
    public void removeProduct(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
