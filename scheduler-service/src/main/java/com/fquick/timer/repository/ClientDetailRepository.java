package com.fquick.timer.repository;

import com.fquick.timer.domain.model.Client;
import com.fquick.timer.domain.model.ClientRegistrationDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
public interface ClientDetailRepository extends CrudRepository<ClientRegistrationDetail, Long> {
    final static String FIND_BY_USE_CASE = "select cd from ClientRegistrationDetail cd , Client c, UseCase u where cd.client.id = c.id and u.id = cd.useCase.id and c.externalId = ?1 and u.name = ?2 and cd.isActive is true";

    @Query(FIND_BY_USE_CASE)
    public List<ClientRegistrationDetail> findByClientExternalIdAndUseCase(String external_id, String useCase);
}
