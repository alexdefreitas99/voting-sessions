package com.alexdefreitas.contract.v1.session;

import com.alexdefreitas.contract.config.DefaultSpringException;
import com.alexdefreitas.contract.v1.session.model.request.SessionRequest;
import com.alexdefreitas.contract.v1.session.model.response.SessionResponse;
import com.alexdefreitas.session.service.SessionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.alexdefreitas.contract.v1.session.mapper.SessionContractMapper.mapFrom;

@RestController
@Api("Session operations")
@RequestMapping("v1/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/agenda/{agenda_id}")
    @ApiOperation(value = "Create a session for an agenda.", response = SessionResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SessionResponse.class)
    })
    public ResponseEntity<SessionResponse> createSession(
            @ApiParam(value = "Session data.", required = true)
            @Valid @RequestBody SessionRequest sessaoRequest,
            @ApiParam(value = "Agenda id.", required = true)
            @PathVariable("agenda_id") Long agendaId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapFrom(sessionService.createVotingSession(mapFrom(sessaoRequest), agendaId)));
    }

    @GetMapping(path = "/{session_id}")
    @ApiOperation(value = "Find session by id.", response = SessionResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SessionResponse.class),
            @ApiResponse(code = 404, message = "Session {session_id} not found.", response = DefaultSpringException.class)
    })
    public ResponseEntity<SessionResponse> findSession(
            @ApiParam(value = "Session id.", required = true)
            @PathVariable("session_id") Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapFrom(sessionService.findSession(id)));
    }
}
