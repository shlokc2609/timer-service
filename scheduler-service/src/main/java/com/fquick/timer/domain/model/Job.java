package com.fquick.timer.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fquick.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * Created by shlok.chaurasia on 04/03/16.
 */
@Data
//@EqualsAndHashCode(callSuper = true)
@Slf4j
@Entity
@Table(name = "job")
public class Job  {

    @Id
    @Column(name = "id")
    @JsonProperty(value = "id")
    private Integer id;

    @Column(name = "job_type")
    String jobType;
}
