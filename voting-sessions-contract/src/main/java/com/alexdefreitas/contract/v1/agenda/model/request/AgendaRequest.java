package com.alexdefreitas.contract.v1.agenda.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRequest {
    @NotEmpty
    private String subject;
}
