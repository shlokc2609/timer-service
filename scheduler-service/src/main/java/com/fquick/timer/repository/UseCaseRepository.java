package com.fquick.timer.repository;

import com.fquick.timer.domain.model.UseCase;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by shlok.chaurasia on 11/03/16.
 */
public interface UseCaseRepository extends CrudRepository<UseCase, Long> {

    List<UseCase> findAllByName(String name);
    UseCase findByName(String name);
}
