package com.alexdefreitas.voting.model;

import com.alexdefreitas.session.model.SessionModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VotingModel {
    private Long id;
    private SessionModel session;
    private String associatedCpf;
    private boolean vote;
}
