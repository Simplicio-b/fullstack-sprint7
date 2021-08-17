package br.com.rchlo.store.dto;

import br.com.rchlo.store.domain.Category;

public class CategoryOrderPositionDto {

    private String name;
    private String slug;

    public CategoryOrderPositionDto(Category category) {
        this.name = category.getName();
        this.slug = category.getSlug();
    }


    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

}
