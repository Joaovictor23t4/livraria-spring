package com.livraria.exceptions.category;

public class CategoryAlreadyDeletedException extends RuntimeException {
    public CategoryAlreadyDeletedException() {
        super("Category already deleted");
    }
}