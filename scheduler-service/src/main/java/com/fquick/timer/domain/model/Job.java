package com.fquick.timer.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fquick.timer.domain.enums.JobStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shlok.chaurasia on 04/03/16.
 */
@Data
@Slf4j
@Entity
@Table(name = "job")
public class Job extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonProperty(value = "client_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Client client;

    @Column(name = "use_case")
    private String useCase;

    @Column(name = "scheduled_time")
    private DateTime scheduledTime;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "message_id")
    private String messageId;

    @Column(name = "status", columnDefinition = "varchar(32)")
    @Enumerated(value = EnumType.STRING)
    private JobStatus status;

    @OneToMany(mappedBy = "job", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.JOIN)
    @JsonProperty(value = "job_attributes")
    private List<JobAttribute> jobAttributes = new ArrayList<>();

}
