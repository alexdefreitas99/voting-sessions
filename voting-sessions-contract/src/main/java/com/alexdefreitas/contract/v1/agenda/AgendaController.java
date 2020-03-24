package com.alexdefreitas.contract.v1.agenda;

import com.alexdefreitas.agenda.service.AgendaService;
import com.alexdefreitas.contract.config.DefaultSpringErrorHandler;
import com.alexdefreitas.contract.v1.agenda.model.request.AgendaRequest;
import com.alexdefreitas.contract.v1.agenda.model.response.AgendaResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.alexdefreitas.contract.v1.agenda.mapper.AgendaContractMapper.mapFrom;

@RestController
@Api("Agenda operations")
@RequestMapping("v1/agenda")
public class AgendaController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AgendaService agendaService;

    @PostMapping
    @ApiOperation(value = "Create a new agenda.", response = AgendaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AgendaResponse.class),
    })
    public ResponseEntity<AgendaResponse> createAgenda(
            @ApiParam(value = "Object for creating agenda.", required = true)
            @RequestBody @Valid AgendaRequest agendaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapFrom(agendaService.createAgenda(mapFrom(agendaRequest))));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get agenda")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AgendaResponse.class),
            @ApiResponse(code = 404, message = "Agenda {agenda_id} not found.", response = DefaultSpringErrorHandler.class)
    })
    public ResponseEntity<AgendaResponse> getAgenda(
            @ApiParam(value = "id.", required = true)
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(mapFrom(agendaService.getAgendaCalculedVotes(id)));
    }
}
