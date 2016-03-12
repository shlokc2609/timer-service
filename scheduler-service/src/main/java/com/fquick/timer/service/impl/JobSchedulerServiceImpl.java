package com.fquick.timer.service.impl;

import com.fquick.timer.Constants;
import com.fquick.timer.domain.enums.JobStatus;
import com.fquick.timer.domain.enums.SubscriptionType;
import com.fquick.timer.domain.model.Client;
import com.fquick.timer.domain.model.ClientRegistrationDetail;
import com.fquick.timer.domain.model.Job;
import com.fquick.timer.domain.model.JobAttribute;
import com.fquick.timer.dto.NotifierDto;
import com.fquick.timer.dto.RegisterSchedulerDto;
import com.fquick.timer.exception.ClientNotFoundException;
import com.fquick.timer.exception.HeaderMappingFailedException;
import com.fquick.timer.exception.NotificationFailedException;
import com.fquick.timer.repository.ClientDetailRepository;
import com.fquick.timer.repository.ClientRepository;
import com.fquick.timer.repository.JobAttributeRepository;
import com.fquick.timer.repository.JobRepository;
import com.fquick.timer.service.JobSchedulerService;
import com.fquick.timer.service.ListenerNotifier;
import com.fquick.timer.service.factory.ListenerNotifierFactory;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shlok.chaurasia on 06/03/16.
 */
@Slf4j
@Component("jobSchedulerService")
public class JobSchedulerServiceImpl implements JobSchedulerService {

    public static Integer maxJobExecutionSize = 10;
    @Autowired
    JobRepository jobRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientDetailRepository clientDetailRepository;

    @Autowired
    JobAttributeRepository jobAttributeRepository;

    @Autowired
    ListenerNotifierFactory listenerNotifierFactory;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Job registerJob(RegisterSchedulerDto registerSchedulerDto) throws ClientNotFoundException {
        log.info("Looking for client: " + registerSchedulerDto.getClientExternalId() + "with Use case: " + registerSchedulerDto.getUseCase());
        List<ClientRegistrationDetail> clientRegistrationDetails = clientDetailRepository.
                findByClientExternalIdAndUseCase(registerSchedulerDto.getClientExternalId(), registerSchedulerDto.getUseCase());
        if (clientRegistrationDetails.size() == 0)
            throw new ClientNotFoundException(registerSchedulerDto.getClientExternalId());
        Client client = clientRegistrationDetails.get(0).getClient();
        log.info("Creating job schedule: ");
        Job job = createJob(client, registerSchedulerDto);
        List<JobAttribute> jobAttributes = new ArrayList<>();
        jobAttributes.add(createJobAttribute(Constants.PAYLOAD, registerSchedulerDto.getPayload(), job));
        job.setJobAttributes(jobAttributes);
        return jobRepository.save(job);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executeJobsForClient(String clientId) {
        Client client = clientRepository.findByExternalId(clientId);
        List<ClientRegistrationDetail> clientRegistrationDetails = client.getClientRegistrationDetails();
        List<Job> eligibleJobs = getListEligibleJobs(clientId);
        for (Job job : eligibleJobs) {
            ClientRegistrationDetail clientRegistrationDetail = getClientRegistrationDetailForUseCase(clientRegistrationDetails, job.getUseCase());
            SubscriptionType subscriptionType = clientRegistrationDetail.getSubscriptionType();
            ListenerNotifier listenerNotifier = listenerNotifierFactory.getNotifier(subscriptionType);
            try {
                listenerNotifier.notify(listenerNotifier.getMappingDto(job, clientRegistrationDetail));
                job.setStatus(JobStatus.COMPLETED);
            } catch (NotificationFailedException ex) {
                log.error("Error while publishing to Varadhi: " + ex.getMessage());
                job.setStatus(JobStatus.FAILED);
            } catch (HeaderMappingFailedException ex) {
                log.error("Error while mapping headers for Varadhi.");
                job.setStatus(JobStatus.FAILED);
            }
            jobRepository.save(job);
        }
    }

    private List<Job> getListEligibleJobs(String clientExternalId) {
        Pageable pageable = new PageRequest(0, maxJobExecutionSize);
        return jobRepository.getEligibleJobs(new DateTime(), JobStatus.SCHEDULED, clientExternalId, pageable);
    }

    private ClientRegistrationDetail getClientRegistrationDetailForUseCase(List<ClientRegistrationDetail> clientRegistrationDetails,
                                                                           String jobUseCase) {
        return clientRegistrationDetails.stream().
                filter(clientRegistrationDetail ->
                        clientRegistrationDetail.getUseCase().getName()
                                .equals(jobUseCase)).collect(Collectors.toList()).get(0);
    }

    private Job createJob(Client client, RegisterSchedulerDto registerSchedulerDto) {
        Job job = new Job();
        job.setClient(client);
        job.setMessageId(registerSchedulerDto.getMessageId());
        job.setScheduledTime(registerSchedulerDto.getScheduledTime());
        job.setUseCase(registerSchedulerDto.getUseCase());
        job.setStatus(JobStatus.SCHEDULED);
        job.setMessageId(registerSchedulerDto.getMessageId());
        job.setRetryCount(1);
        return job;
    }

    private JobAttribute createJobAttribute(String attributeName,
                                            String attributeValue, Job job) {
        JobAttribute jobAttribute = new JobAttribute();
        jobAttribute.setAttributeName(attributeName);
        jobAttribute.setAttributeValue(attributeValue);
        jobAttribute.setJob(job);
        return jobAttribute;
    }
}
