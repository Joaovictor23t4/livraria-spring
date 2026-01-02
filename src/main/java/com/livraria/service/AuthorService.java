package com.livraria.service;

import com.livraria.dto.AuthorCreateDTO;
import com.livraria.dto.AuthorDTO;
import com.livraria.dto.AuthorUpdateDTO;
import com.livraria.entity.Author;
import com.livraria.exceptions.author.AuthorNotFoundException;
import com.livraria.repository.AuthorRepository;
import com.livraria.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;

    public AuthorDTO create(AuthorCreateDTO dto) {
        Author author = new Author(dto.name(), dto.email());

        repository.save(author);

        return toAuthorDTO(author);
    }

    public List<AuthorDTO> findAll() {
        List<Author> authors = repository.findAll();

        return authors.stream().map(this::toAuthorDTO).toList();
    }

    public AuthorDTO findById(Long id) {
        Author author = repository.findById(id).orElseThrow(AuthorNotFoundException::new);

        return toAuthorDTO(author);
    }

    public List<AuthorDTO> findByName(String name) {
        List<Author> authors = repository.findByNameContainingIgnoreCase(name);

        return authors.stream().map(this::toAuthorDTO).toList();
    }

    public List<AuthorDTO> findByEmail(String email) {
        List<Author> authors = repository.findByEmailContaining(email);

        return authors.stream().map(this::toAuthorDTO).toList();
    }

    public List<AuthorDTO> findByNameAndEmail(String name, String email) {
        List<Author> authors = repository.findByNameContainingIgnoreCaseAndEmailContaining(name, email);

        return authors.stream().map(this::toAuthorDTO).toList();
    }

    public AuthorDTO update(Long id, AuthorUpdateDTO dto) throws IllegalAccessException {
        Author entity = repository.findById(id).orElseThrow(AuthorNotFoundException::new);

        ReflectionUtils.updateFields(entity, dto);

        repository.save(entity);

        return toAuthorDTO(entity);
    }

    public void delete(Long id) {
        Author author = repository.findById(id).orElseThrow(AuthorNotFoundException::new);

        repository.delete(author);
    }

    public AuthorDTO toAuthorDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getName(), author.getEmail());
    }
}
