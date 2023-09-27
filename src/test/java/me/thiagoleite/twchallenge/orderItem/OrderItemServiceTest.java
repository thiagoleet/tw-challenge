package me.thiagoleite.twchallenge.orderItem;

import me.thiagoleite.twchallenge.model.entities.Order;
import me.thiagoleite.twchallenge.model.entities.OrderItem;
import me.thiagoleite.twchallenge.model.entities.Product;
import me.thiagoleite.twchallenge.model.repositories.OrderItemRepository;
import me.thiagoleite.twchallenge.model.repositories.OrderRepository;
import me.thiagoleite.twchallenge.services.OrderItemService;
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
public class OrderItemServiceTest {
    @Mock
    private OrderItemRepository repository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderItemService service;

    @Test
    public void findAll_should_return_list() {
        OrderItem item = new OrderItem();
        item.setId(1L);
        item.setQuantity(2);
        item.setOrder(new Order());
        item.setProduct(new Product());

        when(repository.findAll())
                .thenReturn(List.of(item));

        Iterable<OrderItem> expectedOrders = service.findAll();

        assertEquals(List.of(item), expectedOrders);
        verify(repository).findAll();
    }

    @Test
    public void findById_should_return_item() {
        OrderItem item = new OrderItem();
        item.setId(1L);
        item.setQuantity(2);
        item.setOrder(new Order());
        item.setProduct(new Product());

        when(repository.findById(1L))
                .thenReturn(Optional.of(item));

        OrderItem expected = service.findById(1L).get();

        assertEquals(item, expected);
        verify(repository).findById(1L);
    }

    @Test
    public void create_should_return_item() {
        OrderItem newItem = new OrderItem();
        newItem.setId(1L);
        newItem.setQuantity(2);
        newItem.setOrder(new Order());
        newItem.setProduct(new Product());


        when(repository.save(any(OrderItem.class)))
                .thenReturn(newItem);

        OrderItem expected = service.create(new OrderItem());

        assertEquals(newItem, expected);
        verify(repository).save(any(OrderItem.class));
    }

    @Test
    public  void deleteById_should_remove_item() {
        service.deleteById(1L);

        verify(repository, times(1))
                .deleteById(1L);
    }

    @Test
    public  void update_should_change_item() {
        OrderItem updatedItem = new OrderItem();
        updatedItem.setId(1L);
        updatedItem.setQuantity(999);
        updatedItem.setOrder(new Order());
        updatedItem.setProduct(new Product());

        when(repository.save(any(OrderItem.class)))
                .thenReturn(updatedItem);

        when(repository.findById(1L))
                .thenReturn(Optional.of(updatedItem));

        when(orderRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(new Order()));


        Order order = new Order();
        order.setId(1L);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(new Product());
        orderItem.setId(1L);
        orderItem.setQuantity(1);

        OrderItem expected = service.update(1L, orderItem);

        assertEquals(updatedItem, expected);
        verify(repository).save(any(OrderItem.class));
    }
}
