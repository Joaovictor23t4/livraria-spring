package com.livraria.dto;

import jakarta.validation.constraints.NotNull;

public record AuthorCreateDTO(@NotNull String name, String email) {
}
