package com.fquick.timer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fquick.timer.domain.enums.SubscriptionType;
import lombok.Data;

import java.util.Map;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
@Data
public class VaradhiNotifierDto extends NotifierDto{

    @JsonProperty(value = "exchange_name")
    private String exchangeName;

    @JsonProperty(value = "payload")
    private String payload;

    @JsonProperty(value = "group_id")
    private String groupId;

    @JsonProperty(value = "http_method")
    private String httpMethod;

    @JsonProperty(value = "headers")
    private Map<String,String> headers;

    @JsonProperty(value = "uri")
    private String uri;


}
