package com.livraria.controller;

import com.livraria.dto.AuthorCreateDTO;
import com.livraria.dto.AuthorDTO;
import com.livraria.dto.AuthorUpdateDTO;
import com.livraria.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService service;

    @PostMapping("/")
    public ResponseEntity<AuthorDTO> create(@RequestBody @Valid AuthorCreateDTO body) {
        AuthorDTO author = service.create(body);

        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<AuthorDTO>> findAll() {
        List<AuthorDTO> authors = service.findAll();

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> findById(@PathVariable Long id) {
        AuthorDTO author = service.findById(id);

        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id, @RequestBody @Valid AuthorUpdateDTO body) throws IllegalAccessException {
        AuthorDTO author = service.update(id, body);

        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
