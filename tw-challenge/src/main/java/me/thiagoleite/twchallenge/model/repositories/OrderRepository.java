package me.thiagoleite.twchallenge.model.repositories;

import me.thiagoleite.twchallenge.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
