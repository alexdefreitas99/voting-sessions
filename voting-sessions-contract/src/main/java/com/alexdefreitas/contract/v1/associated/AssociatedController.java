package com.alexdefreitas.contract.v1.associated;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/associated")
public class AssociatedController {

    @PostMapping
    public ResponseEntity<String> test(@Valid @RequestBody Object object) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("The object was created! ".concat(object.toString()));
    }
}
