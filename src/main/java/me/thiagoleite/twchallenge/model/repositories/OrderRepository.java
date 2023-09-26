package me.thiagoleite.twchallenge.model.repositories;

import me.thiagoleite.twchallenge.model.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
