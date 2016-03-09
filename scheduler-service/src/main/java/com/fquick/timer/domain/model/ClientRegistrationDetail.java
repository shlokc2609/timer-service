package com.fquick.timer.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fquick.timer.domain.enums.SubscriptionType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */

@Data
@Entity
@Slf4j
@Table(name = "client_registration_detail")
public class ClientRegistrationDetail extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonProperty(value = "client_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "use_case_id")
    @JsonProperty(value = "use_case_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private UseCase useCase;

    @Column(name = "is_active")
    private Boolean isActive;

    @NotNull
    @Column(name = "subscription_type" , columnDefinition = "varchar(32)")
    @Enumerated(value = EnumType.STRING)
    private SubscriptionType subscriptionType;

    @Column(name = "description")
    private String description;

}
