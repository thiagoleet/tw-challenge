package me.thiagoleite.twchallenge.model.repositories;


import me.thiagoleite.twchallenge.model.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
