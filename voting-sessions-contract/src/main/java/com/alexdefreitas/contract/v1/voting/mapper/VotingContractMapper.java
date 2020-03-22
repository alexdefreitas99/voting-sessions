package com.alexdefreitas.contract.v1.voting.mapper;

import com.alexdefreitas.contract.v1.voting.model.request.VotingRequest;
import com.alexdefreitas.contract.v1.voting.model.response.VotingResponse;
import com.alexdefreitas.voting.model.VotingModel;

public class VotingContractMapper {

    public static VotingResponse mapFrom(VotingModel votingModel) {
        return VotingResponse.builder().build();
    }

    public static VotingModel mapFrom(VotingRequest votingRequest) {
        return VotingModel.builder()
                .agendaId(votingRequest.getAgendaId())
                .sessionId(votingRequest.getSessionId())
                .build();
    }
}
