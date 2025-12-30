package com.livraria.service;

import java.util.List;

import com.livraria.dto.CategoryCreateDTO;
import com.livraria.dto.CategoryDTO;
import com.livraria.entity.Category;
import com.livraria.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryDTO> findAllCategories() {
        List<Category> categories = repository.findAll();

        return categories.stream().map(this::toCategoryDTO).toList();
    }

    public CategoryDTO findById(Long id) {
        Category category = repository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        return new CategoryDTO(category.getId(), category.getDescription());
    }

    public CategoryDTO create(CategoryCreateDTO dto) {
        Category entity = new Category(dto.description());

        Category response = repository.save(entity);
        return new CategoryDTO(response.getId(), response.getDescription());
    }

    public CategoryDTO update(Long id, CategoryCreateDTO dto) {
        Category entity = new Category(id, dto.description());

        Category response = repository.save(entity);
        return new CategoryDTO(response.getId(), response.getDescription());
    }

    public void delete(Long id) {
        Category category = repository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        repository.delete(category);
    }

    public CategoryDTO toCategoryDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getDescription());
    }
}
