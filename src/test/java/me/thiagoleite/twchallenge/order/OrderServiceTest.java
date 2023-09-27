package me.thiagoleite.twchallenge.order;


import me.thiagoleite.twchallenge.model.entities.Order;
import me.thiagoleite.twchallenge.model.entities.OrderItem;
import me.thiagoleite.twchallenge.model.repositories.OrderRepository;
import me.thiagoleite.twchallenge.services.OrderService;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService service;


    @Test
    public void findAll_should_return_list() {
        Order order = new Order();
        order.setId(1L);
        order.setItems(new ArrayList<>());

        when(repository.findAll())
                .thenReturn(List.of(order));

        Iterable<Order> expectedOrders = service.findAll();

        assertEquals(List.of(order), expectedOrders);
        verify(repository).findAll();
    }

    @Test
    public void findById_should_return_item() {
        Order order = new Order();
        order.setId(1L);
        order.setItems(new ArrayList<>());

        when(repository.findById(1L))
                .thenReturn(Optional.of(order));

        Order expected = service.findById(1L).get();

        assertEquals(order, expected);
        verify(repository).findById(1L);
    }

    @Test
    public void create_should_return_item() {
        Order newOrder = new Order();
        newOrder.setId(1L);
        newOrder.setItems(new ArrayList<>());


        when(repository.save(any(Order.class)))
                .thenReturn(newOrder);

        Order expected = service.create(new Order());

        assertEquals(newOrder, expected);
        verify(repository).save(any(Order.class));
    }

    @Test
    public  void deleteById_should_remove_item() {
        service.deleteById(1L);

        verify(repository, times(1))
                .deleteById(1L);
    }

    @Test
    public  void update_should_change_item() {
        Order updatedProduct = new Order();
        updatedProduct.setId(1L);
        updatedProduct.setItems(List.of(new OrderItem[]{new OrderItem()}));

        when(repository.save(any(Order.class)))
                .thenReturn(updatedProduct);

        when(repository.findById(1L))
                .thenReturn(Optional.of(updatedProduct));

        Order expected = service.update(1L, new Order());

        assertEquals(updatedProduct, expected);
        verify(repository).save(any(Order.class));
    }
}
