package com.fquick.timer.repository;

import com.fquick.timer.domain.model.Client;
import com.fquick.timer.domain.model.Job;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
public interface ClientRepository extends CrudRepository<Client, Long> {
}
