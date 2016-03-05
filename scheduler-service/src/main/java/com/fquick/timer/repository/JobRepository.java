package com.fquick.timer.repository;

import com.fquick.timer.domain.model.Job;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by shlok.chaurasia on 04/03/16.
 */
public interface JobRepository extends CrudRepository<Job, Long> {
}
