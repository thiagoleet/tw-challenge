package me.thiagoleite.twchallenge.product;

import me.thiagoleite.twchallenge.model.entities.Product;
import me.thiagoleite.twchallenge.model.repositories.ProductRepository;
import me.thiagoleite.twchallenge.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;


    @Test
    public void findAll_should_return_list() {
        Product product = new Product();
        product.setId(1L);
        product.setName("iPad");
        product.setPrice(999);


        when(repository.findAll())
                .thenReturn(List.of(product));

        Iterable<Product> products = service.findAll();

        assertEquals(List.of(product), products);
        verify(repository).findAll();
    }

    @Test
    public void findById_should_return_item() {
        Product product = new Product();
        product.setId(1L);
        product.setName("iPad");
        product.setPrice(999);


        when(repository.findById(1L))
                .thenReturn(Optional.of(product));

        Product expected = service.findById(1L).get();

        assertEquals(product, expected);
        verify(repository).findById(1L);
    }

    @Test
    public void create_should_return_item() throws Exception {
        Product newProduct = new Product();
        newProduct.setId(1L);
        newProduct.setName("iPad");
        newProduct.setPrice(999);


        when(repository.save(any(Product.class)))
                .thenReturn(newProduct);

        Product expected = service.create(new Product("iPad", 999));

        assertEquals(newProduct, expected);
        verify(repository).save(any(Product.class));
    }

    @Test
    public void deleteById_should_remove_item() {
        service.deleteById(1L);

        verify(repository, times(1))
                .deleteById(1L);

    }

    @Test
    public void update_should_change_item() {
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("iPhone");
        updatedProduct.setPrice(999);

        when(repository.save(any(Product.class)))
                .thenReturn(updatedProduct);

        when(repository.findById(1L))
                .thenReturn(Optional.of(updatedProduct));

        Product expected = service.update(1L, new Product("iPad", 999));

        assertEquals(updatedProduct, expected);
        verify(repository).save(any(Product.class));
    }


}
