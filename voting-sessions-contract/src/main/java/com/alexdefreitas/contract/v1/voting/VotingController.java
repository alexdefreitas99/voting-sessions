package com.alexdefreitas.contract.v1.voting;

import com.alexdefreitas.contract.v1.voting.model.request.VotingRequest;
import com.alexdefreitas.contract.v1.voting.model.response.VotingResponse;
import com.alexdefreitas.voting.service.VotingService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.alexdefreitas.contract.v1.voting.mapper.VotingContractMapper.mapFrom;

@RestController
@Api("Session")
@RequestMapping("v1/voting")
public class VotingController {

    @Autowired
    private VotingService votingService;

    @PostMapping("/session/{session_id}/agenda/{agenda_id}")
    @ApiOperation(value = "Vote in an agenda.", response = VotingResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = VotingResponse.class)
    })
    public ResponseEntity<VotingResponse> vote(
            @ApiParam(value = "Vote information.", required = true)
            @Valid @RequestBody VotingRequest votingRequest,
            @ApiParam(value = "Agenda id.", required = true)
            @PathVariable("agenda_id") Long agendaId,
            @ApiParam(value = "Session id.", required = true)
            @PathVariable("session_id") Long sessionId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapFrom(votingService.vote(mapFrom(votingRequest), sessionId, agendaId)));
    }
}
