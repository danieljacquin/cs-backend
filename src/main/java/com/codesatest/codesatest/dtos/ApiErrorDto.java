package com.codesatest.codesatest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiErrorDto {
    private String message;
    private List<String> messages;
}
