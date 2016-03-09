package com.fquick.timer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fquick.timer.domain.enums.SubscriptionType;
import lombok.Data;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class RegisterClientDto {

    @JsonProperty(value = "client_use_case")
    private String clientUseCase;

    @JsonProperty(value = "client_use_case_description")
    private String clientUseCaseDescription;

    @JsonProperty(value = "is_active")
    private Boolean isActive;

    @JsonProperty(value = "subscription_type")
    private SubscriptionType subscriptionType;

    @JsonProperty(value = "url")
    private String url;

    @JsonProperty(value = "method")
    private String method;

    @JsonProperty(value = "exchange_name")
    private String exchange_name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "client_external_id")
    private String clientExternalId;
}
