package com.fquick.timer.repository;

import com.fquick.timer.domain.model.UseCase;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by shlok.chaurasia on 11/03/16.
 */
public interface UseCaseRepository extends CrudRepository<UseCase, Long> {

    public UseCase findByName(String name);
}
