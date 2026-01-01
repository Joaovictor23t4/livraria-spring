package com.livraria.service;

import java.util.List;
import java.util.stream.Collectors;

import com.livraria.dto.CategoryCreateDTO;
import com.livraria.dto.CategoryDTO;
import com.livraria.entity.Category;
import com.livraria.exceptions.category.CategoryNotFoundException;
import com.livraria.repository.CategoryRepository;
import com.livraria.utils.ReflectionUtils;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryDTO> findAllCategories() {
        List<Category> categories = repository.findAll();

        return categories.stream().map(this::toCategoryDTO).collect(Collectors.toList());
    }

    public CategoryDTO findById(Long id) {
        Category category = repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        return new CategoryDTO(category.getId(), category.getDescription());
    }

    public CategoryDTO create(CategoryCreateDTO dto) {
        Category entity = new Category(dto.description());

        Category response = repository.save(entity);
        return new CategoryDTO(response.getId(), response.getDescription());
    }

    public CategoryDTO update(Long id, CategoryCreateDTO dto) throws IllegalAccessException {
        Category entity = repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        ReflectionUtils.updateFields(entity, dto);

        Category response = repository.save(entity);
        return new CategoryDTO(response.getId(), response.getDescription());
    }

    public void delete(Long id) {
        Category category = repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        repository.delete(category);
    }

    public CategoryDTO toCategoryDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getDescription());
    }
}
