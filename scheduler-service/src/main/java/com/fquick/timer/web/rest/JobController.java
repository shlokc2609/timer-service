package com.fquick.timer.web.rest;
import com.fquick.timer.domain.model.Job;
import com.fquick.timer.repository.JobRepository;
import com.fquick.timer.service.JobSchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shlok.chaurasia on 04/03/16.
 */
@RestController
@RequestMapping("/job")
@Slf4j
public class JobController {

//    @Autowired
//    JobSchedulerService jobSchedulerService;

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public ResponseEntity<String> scheduleTask() {
        //Job job = jobRepository.findOne(1);
//        log.info(job.getJobType());
        return new ResponseEntity("test", HttpStatus.NO_CONTENT);
    }

    // Add Execute API.
    //Add Search

}
