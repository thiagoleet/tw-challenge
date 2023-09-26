package me.thiagoleite.twchallenge.services;

import me.thiagoleite.twchallenge.model.entities.Product;
import me.thiagoleite.twchallenge.model.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Iterable<Product> findAll() {
        return repository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public Product update(Product product, Long id) {
        Optional<Product> exists = repository.findById(id);

        if (exists.isPresent()) {
            product.setId(id);
            repository.save(product);

            return product;
        }

        return null;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
