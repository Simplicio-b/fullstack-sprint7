package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Color;
import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.dto.ProductByColorDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/schema.sql")
@ActiveProfiles("test")
class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        entityManager.persist(new Product(7L,
                "Jaqueta Puffer Juvenil Com Capuz Super Mario Branco",
                "A Jaqueta Puffer Juvenil Com Capuz Super Mario Branco é confeccionada em material sintético.",
                "jaqueta-puffer-juvenil-com-capuz-super-mario-branco-13834193_sku",
                "Nintendo",
                new BigDecimal("199.90"),
                null,
                Color.WHITE,
                147));

        entityManager.persist(new Product(1L,
                "Regata Infantil Mario Bros Branco",
                "A Regata Infantil Mario Bros Branco é confeccionada em fibra natural. Aposte!",
                "regata-infantil-mario-bros-branco-14040174_sku",
                "Nintendo",
                new BigDecimal("29.90"),
                null,
                Color.WHITE,
                98));

        entityManager.persist(new Product(3L,
                "Regata Infantil Mario Bros Vermelha",
                "A Regata Infantil Mario Bros Branco é confeccionada em fibra natural. Aposte!",
                "regata-infantil-mario-bros-branco-14040174_sku",
                "Nintendo",
                new BigDecimal("29.90"),
                null,
                Color.RED,
                98));

        entityManager.persist(new Product(4L,
                "Regata Infantil Mario Bros Azul",
                "A Regata Infantil Mario Bros Branco é confeccionada em fibra natural. Aposte!",
                "regata-infantil-mario-bros-branco-14040174_sku",
                "Nintendo",
                new BigDecimal("39.90"),
                null,
                Color.BLUE,
                98));
    }

//    @Test
//    void shouldListAllProductsOrderedByName() {
//        List<Product> products = productRepository.findAllByOrderByName();
//
//        assertEquals(4, products.size());
//
//        Product firstProduct = products.get(0);
//        assertEquals(7L, firstProduct.getCode());
//        assertEquals("Jaqueta Puffer Juvenil Com Capuz Super Mario Branco", firstProduct.getName());
//
//        Product secondProduct = products.get(1);
//        assertEquals(4L, secondProduct.getCode());
//        assertEquals("Regata Infantil Mario Bros Azul", secondProduct.getName());
//    }

    @Test
    void shouldReportsProductsByColor() {
        List<ProductByColorDto> products = productRepository.productsByColor();
        System.out.println(products);
        products.forEach(p -> {
            System.out.println(p.getColor());
            System.out.println(p.getAmount());
        });

        assertEquals(3, products.size());

        assertEquals("Azul", products.get(0).getColor());
        assertEquals(1, products.get(0).getAmount());

        assertEquals("Vermelha", products.get(1).getColor());
        assertEquals(1, products.get(1).getAmount());

        assertEquals("Branca", products.get(2).getColor());
        assertEquals(2, products.get(2).getAmount());
    }
}