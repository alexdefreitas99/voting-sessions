package com.alexdefreitas.contract.v1.session.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionRequest {
    @Value("1")
    private Integer minuteDuration;

    @NotNull
    private Long agenda;
}