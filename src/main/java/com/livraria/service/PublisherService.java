package com.livraria.service;

import com.livraria.dto.PublisherCreateDTO;
import com.livraria.dto.PublisherDTO;
import com.livraria.entity.Category;
import com.livraria.entity.Publisher;
import com.livraria.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    PublisherRepository repository;

    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    public List<PublisherDTO> findAll() {
        List<Publisher> publishers = repository.findAll();

        return publishers.stream().map(this::toPublisherDTO).toList();
    }

    public PublisherDTO findById(Long id) {
        Publisher publisher = repository.findById(id).orElseThrow(() -> new RuntimeException("Publisher not found"));
        
        return toPublisherDTO(publisher);
    }
    
    public PublisherDTO create(PublisherCreateDTO dto) {
        Publisher entity = new Publisher(dto.name(), dto.site());
        
        Publisher publisher = repository.save(entity);
        
        return toPublisherDTO(publisher);
    }
    
    public PublisherDTO update(Long id, PublisherCreateDTO dto) {
        Publisher entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Publiser not found"));

        if (dto.name() != null) {
            entity.setName(dto.name());
        }
        if (dto.site() != null) {
            entity.setSite(dto.site());
        }

        Publisher publisher = repository.save(entity);

        return toPublisherDTO(publisher);
    }

    public void delete(Long id) {
        Publisher publisher = repository.findById(id).orElseThrow(() -> new RuntimeException("Publisher not found"));
        repository.delete(publisher);
    }

    PublisherDTO toPublisherDTO(Publisher publisher) {
        return new PublisherDTO(publisher.getId(), publisher.getName(), publisher.getSite());
    }
}
