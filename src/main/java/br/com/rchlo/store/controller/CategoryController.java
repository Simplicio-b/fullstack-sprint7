package br.com.rchlo.store.controller;

import br.com.rchlo.store.dto.CategoryOrderPositionDto;
import br.com.rchlo.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping
    public List<CategoryOrderPositionDto> categories() {
        return repository.findAllByOrderByPosition().stream().map(CategoryOrderPositionDto::new).collect(Collectors.toList());
    }

}
