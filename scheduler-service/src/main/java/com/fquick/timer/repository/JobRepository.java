package com.fquick.timer.repository;

import com.fquick.timer.domain.enums.JobStatus;
import com.fquick.timer.domain.model.Job;
import org.joda.time.DateTime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by shlok.chaurasia on 04/03/16.
 */
public interface JobRepository extends CrudRepository<Job, Long> {
    public final static String JOB_QUERY = "select j from Job j where j.scheduledTime < ?1 and j.status = ?2 and j.client.externalId = ?3";

    @Query(JOB_QUERY)
    public List<Job> getEligibleJobs(DateTime time, JobStatus status, String externalId, Pageable limit);
}
