package com.livraria.exceptions.publisher;

public class PublisherNotFoundException extends RuntimeException {
    public PublisherNotFoundException() {
        super("Publisher not found");
    }
}
