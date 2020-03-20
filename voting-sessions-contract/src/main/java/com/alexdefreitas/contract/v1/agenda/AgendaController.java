package com.alexdefreitas.contract.v1.agenda;

import com.alexdefreitas.agenda.service.AgendaService;
import com.alexdefreitas.contract.v1.agenda.mapper.AgendaMapper;
import com.alexdefreitas.contract.v1.agenda.model.request.AgendaRequest;
import com.alexdefreitas.contract.v1.agenda.model.response.AgendaResponse;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
