package br.com.rchlo.store.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
    @Id
    private Long id;
    private String name;
    private String slug;
    private int position;

    public Category() {
    }

    public Category(Long id, String name, String slug, int position) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public int getPosition() {
        return position;
    }
}
