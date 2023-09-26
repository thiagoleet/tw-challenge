package me.thiagoleite.twchallenge.model.repositories;

import me.thiagoleite.twchallenge.model.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
