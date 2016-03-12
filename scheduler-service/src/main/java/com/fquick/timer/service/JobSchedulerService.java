package com.fquick.timer.service;

import com.fquick.timer.domain.model.Job;
import com.fquick.timer.dto.RegisterSchedulerDto;
import com.fquick.timer.exception.ClientNotFoundException;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
public interface JobSchedulerService {

    Job registerJob(RegisterSchedulerDto registerSchedulerDto) throws ClientNotFoundException;
    void executeJobsForClient(String ClientId);
}
