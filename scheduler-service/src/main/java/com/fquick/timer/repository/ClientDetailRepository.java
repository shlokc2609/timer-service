package com.fquick.timer.repository;

import com.fquick.timer.domain.model.Client;
import com.fquick.timer.domain.model.ClientRegistrationDetail;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
public interface ClientDetailRepository extends CrudRepository<ClientRegistrationDetail, Long> {

}
