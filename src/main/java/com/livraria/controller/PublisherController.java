package com.livraria.controller;

import com.livraria.dto.PublisherCreateDTO;
import com.livraria.dto.PublisherDTO;
import com.livraria.service.PublisherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    PublisherService service;

    PublisherController(PublisherService service) {
        this.service = service;
    }
    @GetMapping("/")
    public ResponseEntity<List<PublisherDTO>> findAll() {
        List<PublisherDTO> publishers = service.findAll();

        return new ResponseEntity<>(publishers,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> findById(@PathVariable Long id) {
        PublisherDTO publisher = service.findById(id);

        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<PublisherDTO> create(@RequestBody @Valid PublisherCreateDTO body) {
        PublisherDTO publisher = service.create(body);

        return new ResponseEntity<>(publisher, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PublisherDTO> update(@PathVariable Long id, @RequestBody @Valid PublisherCreateDTO body) throws IllegalAccessException {
        PublisherDTO publisher = service.update(id, body);

        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
