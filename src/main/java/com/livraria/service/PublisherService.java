package com.livraria.service;

import com.livraria.dto.PublisherCreateDTO;
import com.livraria.dto.PublisherDTO;
import com.livraria.entity.Publisher;
import com.livraria.exceptions.publisher.PublisherNotFoundException;
import com.livraria.repository.PublisherRepository;
import com.livraria.utils.ReflectionUtils;
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
        Publisher publisher = repository.findById(id).orElseThrow(PublisherNotFoundException::new);
        
        return toPublisherDTO(publisher);
    }
    
    public PublisherDTO create(PublisherCreateDTO dto) {
        Publisher entity = new Publisher(dto.name(), dto.site());
        
        Publisher publisher = repository.save(entity);
        
        return toPublisherDTO(publisher);
    }
    
    public PublisherDTO update(Long id, PublisherCreateDTO dto) throws IllegalAccessException {
        Publisher entity = repository.findById(id).orElseThrow(PublisherNotFoundException::new);

        ReflectionUtils.updateFields(entity, dto);

        Publisher publisher = repository.save(entity);

        return toPublisherDTO(publisher);
    }

    public void delete(Long id) {
        Publisher publisher = repository.findById(id).orElseThrow(PublisherNotFoundException::new);
        repository.delete(publisher);
    }

    PublisherDTO toPublisherDTO(Publisher publisher) {
        return new PublisherDTO(publisher.getId(), publisher.getName(), publisher.getSite());
    }
}
