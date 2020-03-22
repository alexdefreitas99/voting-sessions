package com.alexdefreitas.contract.v1.voting;

import com.alexdefreitas.contract.v1.voting.model.request.VotingRequest;
import com.alexdefreitas.contract.v1.voting.model.response.VotingResponse;
import com.alexdefreitas.voting.service.VotingService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.alexdefreitas.contract.v1.voting.mapper.VotingContractMapper.mapFrom;

@RestController
@Api("Session")
@RequestMapping("v1/voting")
public class VotingController {

    @Autowired
    private VotingService votingService;

    @PostMapping
    @ApiOperation(value = "Vote in an agenda.", response = VotingResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = VotingResponse.class)
    })
    public ResponseEntity<VotingResponse> vote(
            @ApiParam(value = "Vote information.", required = true)
            @Valid @RequestBody VotingRequest votingRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapFrom(votingService.vote(mapFrom(votingRequest))));
    }
}
