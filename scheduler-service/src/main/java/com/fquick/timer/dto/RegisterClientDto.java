package com.fquick.timer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fquick.timer.domain.enums.SubscriptionType;
import lombok.Data;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class RegisterClientDto {

    private String clientUseCase;
    private String clientUseCaseDescription;
    private Boolean isActive;
    private SubscriptionType subscriptionType;
    private String subscriptionDescription;
    private String clientExternalId;

}
