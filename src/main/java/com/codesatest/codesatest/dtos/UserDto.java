package com.codesatest.codesatest.dtos;

import com.codesatest.codesatest.enums.statusEnum;
import com.codesatest.codesatest.model.RolesModel;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    private Long id;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "State is required")
    private statusEnum state;

    @Email
    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Rol is required")
    private RolesModel rolesModel;
}
