package com.alexdefreitas.contract.v1.agenda.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRequest {
    @NotNull(message = "The subject of the agenda cannot be null")
    private String subject;
}
