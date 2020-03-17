package com.alexdefreitas.contract;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SessionController {

    @PostMapping
    public ResponseEntity<String> test(@Valid @RequestBody Object object) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("The object was created! ".concat(object.toString()));
    }
}
