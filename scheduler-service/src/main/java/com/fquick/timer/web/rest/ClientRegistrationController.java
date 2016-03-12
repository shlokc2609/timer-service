package com.fquick.timer.web.rest;

import com.codahale.metrics.annotation.Metered;
import com.fquick.timer.domain.model.ClientRegistrationDetail;
import com.fquick.timer.dto.RegisterClientDto;
import com.fquick.timer.service.ClientService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
@RestController
@RequestMapping("/client")
@Slf4j
public class ClientRegistrationController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "Create and Register Client and Use case for timer service",
            response = ClientRegistrationDetail.class, produces = MediaType.APPLICATION_JSON_VALUE)
    @Metered(name = "registerClient", absolute = true)
    public ResponseEntity registerClient(@RequestBody @Valid RegisterClientDto registerClientDto) {
        log.info("Registering use case: " + registerClientDto.getClientUseCase() + " for Client: " + registerClientDto.getClientExternalId());
        ClientRegistrationDetail clientRegistrationDetail = clientService.registerClient(registerClientDto);
        return new ResponseEntity(clientRegistrationDetail, HttpStatus.CREATED);
    }


}
