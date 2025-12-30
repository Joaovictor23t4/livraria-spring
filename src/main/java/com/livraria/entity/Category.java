package com.livraria.entity;

import jakarta.persistence.*;

import java.io.Serial;

@Entity
@Table(name="CATEGORY")
public class Category {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name="DESCRIPTION", length = 100, nullable = false)
    private String description;

    protected Category() {

    }

    public Category(String description) {
        this.description = description;
    }

    public Category(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}