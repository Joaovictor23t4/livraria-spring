package com.livraria.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryCreateDTO(@NotNull String description) {

}
