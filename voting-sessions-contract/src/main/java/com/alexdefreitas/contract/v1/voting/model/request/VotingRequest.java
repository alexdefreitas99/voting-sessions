package com.alexdefreitas.contract.v1.voting.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingRequest {
    @NotEmpty
    private String associatedCpf;
    @NotNull
    private boolean vote;
}
