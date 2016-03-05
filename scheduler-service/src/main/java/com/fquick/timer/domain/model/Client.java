package com.fquick.timer.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
@Data
@Entity
@Slf4j
@Table(name = "client")
public class Client extends BaseEntity{

    @Column(name = "client_external_id")
     String clientExternalId;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.JOIN)
    @JsonProperty(value = "client_registration_details")
    private List<ClientRegistrationDetail> clientRegistrationDetails = new ArrayList<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.JOIN)
    @JsonProperty(value = "jobs")
    private List<Job> jobs = new ArrayList<>();

}
