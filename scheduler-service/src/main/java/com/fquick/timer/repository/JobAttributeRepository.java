package com.fquick.timer.repository;

import com.fquick.timer.domain.model.Job;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by shlok.chaurasia on 08/03/16.
 */
public interface JobAttributeRepository extends CrudRepository<Job, Long> {

}
