package com.fquick.timer.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shlok.chaurasia on 11/03/16.
 */

@Data
@Entity
@Slf4j
@Table(name = "use_case")
public class UseCase extends BaseEntity{

    @Column(name = "url")
    private String url;

    @Column(name = "exchange_name")
    private String exchangeName;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "method")
    private String method;

    @Column(name = "header")
    private String header;

    @OneToMany(mappedBy = "useCase", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.JOIN)
    @JsonProperty(value = "client_registration_details")
    private List<ClientRegistrationDetail> clientRegistrationDetails = new ArrayList<>();


}
