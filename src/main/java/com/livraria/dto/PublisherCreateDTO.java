package com.livraria.dto;

import jakarta.validation.constraints.NotNull;

public record PublisherCreateDTO(@NotNull String name, String site) {

}