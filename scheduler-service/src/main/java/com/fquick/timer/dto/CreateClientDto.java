package com.fquick.timer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateClientDto {
    @JsonProperty(value = "client_external_id")
    String clientExternalId;
}
