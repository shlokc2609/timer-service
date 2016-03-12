package com.fquick.timer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fquick.timer.domain.enums.SubscriptionType;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class RegisterClientDto {

    @NotNull
    @JsonProperty(value = "client_use_case")
    private String clientUseCase;

    @JsonProperty(value = "client_use_case_description")
    private String clientUseCaseDescription;

    @JsonProperty(value = "is_active")
    private Boolean isActive = true;

    @JsonProperty(value = "subscription_type")
    private SubscriptionType subscriptionType;

    @NotNull
    @JsonProperty(value = "url")
    private String url;

    @JsonProperty(value = "header")
    private String header;

    @NotNull
    @JsonProperty(value = "method")
    private String method;

    @NotNull
    @JsonProperty(value = "exchange_name")
    private String exchange_name;

    @JsonProperty(value = "description")
    private String description;

    @NotNull
    @JsonProperty(value = "client_external_id")
    private String clientExternalId;
}
