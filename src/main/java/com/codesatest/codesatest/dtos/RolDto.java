package com.codesatest.codesatest.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class RolDto {
    private Long id;

    @NotNull(message = "rol name is required")
    private String name;
}
