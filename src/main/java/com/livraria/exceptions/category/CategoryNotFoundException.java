package com.livraria.exceptions.category;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super("Category not found");
    }
}