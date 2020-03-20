package com.alexdefreitas.contract.v1.agenda;

import com.alexdefreitas.agenda.service.AgendaService;
import com.alexdefreitas.contract.config.DefaultSpringException;
import com.alexdefreitas.contract.v1.agenda.mapper.AgendaMapper;
import com.alexdefreitas.contract.v1.agenda.model.request.AgendaRequest;
import com.alexdefreitas.contract.v1.agenda.model.response.AgendaResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api("Agenda")
@RequestMapping("v1/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PostMapping
    @ApiOperation(value = "Create a new agenda.", response = AgendaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AgendaResponse.class),
    })
    public ResponseEntity<AgendaResponse> postAgenda(@ApiParam(value = "Object for creating agenda.", required = true)
                                                     @RequestBody @Valid AgendaRequest agendaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AgendaMapper.mapFrom(agendaService.postAgenda(AgendaMapper.mapFrom(agendaRequest))));
    }

    @GetMapping
    @ApiOperation(value = "Find all agenda.", response = AgendaResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AgendaResponse.class, responseContainer = "List"),
    })
    public ResponseEntity<List<AgendaResponse>> findAllAgenda() {
        return ResponseEntity.ok(AgendaMapper.mapFrom(agendaService.findAll()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get pauta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AgendaResponse.class),
            @ApiResponse(code = 404, message = "Agenda not found.", response = DefaultSpringException.class)
    })
    public ResponseEntity<AgendaResponse> buscarPautaComputada(@ApiParam(value = "id.", required = true)
                                                               @PathVariable("id") Long id) {
        return ResponseEntity.ok(AgendaMapper.mapFrom(agendaService.findAgenda(id)));
    }
}
