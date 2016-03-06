package com.fquick.timer.service.impl;

import com.fquick.timer.domain.enums.JobStatus;
import com.fquick.timer.domain.enums.SubscriptionType;
import com.fquick.timer.domain.model.Client;
import com.fquick.timer.domain.model.ClientRegistrationDetail;
import com.fquick.timer.domain.model.Job;
import com.fquick.timer.dto.NotifierDto;
import com.fquick.timer.dto.RegisterSchedulerDto;
import com.fquick.timer.exception.ClientNotFoundException;
import com.fquick.timer.exception.NotificationFailedException;
import com.fquick.timer.repository.ClientRepository;
import com.fquick.timer.repository.JobRepository;
import com.fquick.timer.service.JobSchedulerService;
import com.fquick.timer.service.ListenerNotifier;
import com.fquick.timer.service.factory.ListenerNotifierFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shlok.chaurasia on 06/03/16.
 */
@Slf4j
@Component("jobSchedulerService")
public class JobSchedulerServiceImpl implements JobSchedulerService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ListenerNotifierFactory listenerNotifierFactory;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Job registerJob(RegisterSchedulerDto registerSchedulerDto) throws ClientNotFoundException {
        Client client = clientRepository.findByExternalId(registerSchedulerDto.getClientExternalId());
        log.info("Looking for client: " + registerSchedulerDto.getClientExternalId());
        if (client == null)
            throw new ClientNotFoundException(registerSchedulerDto.getClientExternalId());
        log.info("creating job schedule");
        Job job = new Job();
        job.setClient(client);
        job.setMessageId(registerSchedulerDto.getMessageId());
        job.setPayload(registerSchedulerDto.getPayload());
        job.setScheduledTime(registerSchedulerDto.getScheduledTime());
        job.setUseCase(registerSchedulerDto.getUseCase());
        job.setStatus(JobStatus.SCHEDULED);
        job.setMessageId(registerSchedulerDto.getMessageId());
        job.setRetryCount(1);
        return jobRepository.save(job);
    }

    @Override
    public void executeJobsForClient(String clientId) {
        Client client = clientRepository.findByExternalId(clientId);
        List<ClientRegistrationDetail> clientRegistrationDetails = client.getClientRegistrationDetails();
        for (Job job : getListEligibleJobs()) {
            SubscriptionType subscriptionType = getSubscriptionTypeForUseCase(clientRegistrationDetails, job.getUseCase());
            ListenerNotifier listenerNotifier = listenerNotifierFactory.getNotifier(subscriptionType);
            try {
                listenerNotifier.notify(getNotifierDto());
            }
            catch(NotificationFailedException ex)
            {
                ex.printStackTrace();;
                job.setStatus(JobStatus.FAILED);
                jobRepository.save(job);
            }
        }
    }

    //ToDO implement
    private List<Job> getListEligibleJobs() {
        return null;
    }

    private SubscriptionType getSubscriptionTypeForUseCase(List<ClientRegistrationDetail> clientRegistrationDetails,
                                                           String jobUseCase) {
        return clientRegistrationDetails.stream().
                filter(clientRegistrationDetail ->
                        clientRegistrationDetail.getClientUseCase()
                                .equals(jobUseCase)).collect(Collectors.toList()).get(0).getSubscriptionType();
    }

    //ToDO implement
    private NotifierDto getNotifierDto() {
        return null;
    }
}
