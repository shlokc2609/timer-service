package com.fquick.timer.web.rest;

import com.codahale.metrics.annotation.Metered;
import com.fquick.timer.domain.model.Job;
import com.fquick.timer.service.JobSchedulerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shlok.chaurasia on 08/03/16.
 */
@RestController
@RequestMapping("/internal")
@Slf4j
public class InternalApi {
    @Autowired
    JobSchedulerService jobSchedulerService;

    @RequestMapping(value = "/{client_id}/execute", method = RequestMethod.POST)
    @ApiOperation(value = "Execute eligible tasks for client",
            response = Job.class, produces = MediaType.APPLICATION_JSON_VALUE)
    @Metered(name = "executeTask", absolute = true)
    public ResponseEntity executeTask(@PathVariable("client_id") String clientId) {
        jobSchedulerService.executeJobsForClient(clientId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
