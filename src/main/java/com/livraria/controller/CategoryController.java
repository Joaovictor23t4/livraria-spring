package com.livraria.controller;

import java.util.List;

import com.livraria.dto.CategoryCreateDTO;
import com.livraria.dto.CategoryDTO;
import com.livraria.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> findAllCategories() {
        List<CategoryDTO> categories = service.findAllCategories();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        CategoryDTO category = service.findById(id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> create(@RequestBody @Valid CategoryCreateDTO body) {
        CategoryDTO category = service.create(body);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody @Valid CategoryCreateDTO body) throws IllegalAccessException {
        CategoryDTO category = service.update(id, body);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}