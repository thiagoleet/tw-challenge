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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    public void findAll_should_return_list() {
        Product product = new Product();
        product.setId(1L);
        product.setName("iPad");
        product.setPrice(999);


        when(productRepository.findAll())
                .thenReturn(List.of(product));

        Iterable<Product> products = productService.findAll();

        assertEquals(List.of(product), products);
        verify(productRepository).findAll();
    }

    @Test
    public void findById_should_return_item() {
        Product product = new Product();
        product.setId(1L);
        product.setName("iPad");
        product.setPrice(999);


        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        Product expected = productService.findById(1L).get();

        assertEquals(product, expected);
        verify(productRepository).findById(1L);
    }

    @Test
    public void create_should_return_item() {
        Product product = new Product();
        product.setId(1L);
        product.setName("iPad");
        product.setPrice(999);


        when(productRepository.save(new Product("iPad", 999)))
                .thenReturn(product);

        Product expected = productService.create(new Product("iPad", 999));

        assertEquals(product, expected);
        verify(productRepository).save(new Product("iPad", 999));
    }

    @Test
    public  void deleteById_should_remove_item() {
        // TODO:
    }

    @Test
    public  void update_should_change_item() {
        // TODO:
    }


}
