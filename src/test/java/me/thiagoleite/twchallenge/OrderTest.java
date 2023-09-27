package me.thiagoleite.twchallenge;

import me.thiagoleite.twchallenge.model.entities.Order;
import me.thiagoleite.twchallenge.model.entities.OrderItem;
import me.thiagoleite.twchallenge.model.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class OrderTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testSaveOrder() {
        Order order = new Order();

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(23);
        order.add(orderItem);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setQuantity(32);
        order.add(orderItem2);

        orderRepository.save(order);
    }


    @Test
    void testUpdateOrder() {
        Order order = orderRepository
                .findAll()
                .iterator()
                .next();

        order.setDate(new Date());
        orderRepository.save(order);
    }

    @Test
    void testGetAllOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        orders.forEach((o) -> {
            System.out.println("order id :: " + o.getId());
            o.getItems().forEach((orderItem -> {
                System.out.println("orderItem :: " + orderItem.getId());
            }));
        });
    }

    @Test
    void testDeleteOrder() {
        orderRepository.deleteById(1L);
    }
}
