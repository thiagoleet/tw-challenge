package me.thiagoleite.twchallenge.model.repositories;


import me.thiagoleite.twchallenge.model.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
