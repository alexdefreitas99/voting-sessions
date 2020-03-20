package com.alexdefreitas.contract.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultSpringException {
    private Long timestamp;
    private Long status;
    private String error;
    private String message;
    private String path;
}
