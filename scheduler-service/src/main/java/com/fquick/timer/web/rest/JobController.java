package com.fquick.timer.web.rest;

import com.codahale.metrics.annotation.Metered;
import com.fquick.timer.domain.model.Job;
import com.fquick.timer.dto.RegisterSchedulerDto;
import com.fquick.timer.exception.ClientNotFoundException;
import com.fquick.timer.service.JobSchedulerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by shlok.chaurasia on 04/03/16.
 */
@RestController
@RequestMapping("/job")
@Slf4j
public class JobController {

    @Autowired
    JobSchedulerService jobSchedulerService;


    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    @ApiOperation(value = "Create a scheduled Task for client",
            response = Job.class, produces = MediaType.APPLICATION_JSON_VALUE)
    @Metered(name = "scheduleTask", absolute = true)
    public ResponseEntity scheduleTask(@RequestBody RegisterSchedulerDto registerSchedulerDto) {
        try {
            Job job = jobSchedulerService.registerJob(registerSchedulerDto);
            return new ResponseEntity(job, HttpStatus.CREATED);
        } catch (ClientNotFoundException exception) {
            return new ResponseEntity("No client found with external id :" + registerSchedulerDto.getClientExternalId() +
                    " and Use case: " + registerSchedulerDto.getUseCase(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{client_id}/execute", method = RequestMethod.POST)
    @ApiOperation(value = "execute eligible tasks for client",
            response = Job.class, produces = MediaType.APPLICATION_JSON_VALUE)
    @Metered(name = "executeTask", absolute = true)
    public ResponseEntity executeTask(@PathVariable("client_id") String clientId) {
        jobSchedulerService.executeJobsForClient(clientId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
