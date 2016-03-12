package com.fquick.timer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fquick.timer.domain.enums.SubscriptionType;
import lombok.Data;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
@Data
public class NotifierDto {

    @JsonProperty(value = "subscription_type")
    private SubscriptionType subscriptionType;



}
