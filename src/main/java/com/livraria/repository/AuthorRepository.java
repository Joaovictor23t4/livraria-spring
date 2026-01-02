package com.livraria.repository;

import com.livraria.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByNameContainingIgnoreCase(String name);

    List<Author> findByEmailContaining(String email);

    List<Author> findByNameContainingIgnoreCaseAndEmailContaining(String name, String email);
}
