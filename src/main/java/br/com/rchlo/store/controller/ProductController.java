package br.com.rchlo.store.controller;

import br.com.rchlo.store.dto.ProductByColorDto;
import br.com.rchlo.store.dto.ProductDto;
import br.com.rchlo.store.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    @Cacheable(value = "list_all_products")
    public List<ProductDto> products(@PageableDefault(direction = Sort.Direction.DESC) Pageable pagination) {
        return productRepository.findAllByOrderByName(pagination).stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/products-pages")
    public List<ProductDto> productsPage(@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pagination) {
        return productRepository.findAllByOrderByName(pagination).stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/reports/products/by-color")
    public List<ProductByColorDto> productByColorReport() {
        return productRepository.productsByColor();
    }

    @GetMapping("/products/clear-cache")
    @CacheEvict(value = "list_all_products", allEntries = true)
    public String clearCache() {
        return "<h2>Cache Limpa</h2>";
    }

    @GetMapping("/products-imgs")
    public String productImg() {

        return "/products-imgs";
    }

}
