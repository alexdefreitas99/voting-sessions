package com.alexdefreitas.contract.v1.voting.model.request;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "Associated cpf to vote", example = "76399717890", required = true)
    private String associatedCpf;
    @NotNull
    @ApiModelProperty(value = "Associated agreement",
            allowableValues = "true or false", required = true)
    private boolean vote;
}
