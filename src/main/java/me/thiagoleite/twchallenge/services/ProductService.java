package me.thiagoleite.twchallenge.services;

import me.thiagoleite.twchallenge.model.entities.Product;
import me.thiagoleite.twchallenge.model.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements ApiService<Product> {

    @Autowired
    private ProductRepository repository;

    public Iterable<Product> findAll() {
        return repository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public Product save(Product data) {
        return repository.save(data);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Product update(Long id, Product updatedData) {
        Optional<Product> existingProduct = repository.findById(id);
        if (existingProduct.isPresent()) {
            updatedData.setId(id);
            return repository.save(updatedData);
        } else {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
    }
}
