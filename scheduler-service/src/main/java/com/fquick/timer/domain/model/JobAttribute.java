package com.fquick.timer.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * Created by shlok.chaurasia on 08/03/16.
 */
@Data
@Entity
@Slf4j
@Table(name = "job_attribute")
public class JobAttribute extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "job_id")
    @JsonProperty(value = "job_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Job job;

    @Column(name = "attribute_name")
    @JsonProperty(value = "attribute_name")
    private String attributeName;

    @Column(name = "attribute_value", columnDefinition = "LONGTEXT")
    @JsonProperty(value = "attribute_value")
    private String attributeValue;


}
