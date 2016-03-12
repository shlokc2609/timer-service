package com.fquick.timer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fquick.timer.domain.enums.JobStatus;
import lombok.Data;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
@Data
public class RegisterSchedulerDto {

    @NotNull
    @JsonProperty(value = "client_external_id")
    private String clientExternalId;

    @NotNull
    @JsonProperty(value = "scheduled_time")
    private DateTime scheduledTime;

    @NotNull
    @JsonProperty(value = "use_case")
    private String useCase;

    @JsonProperty(value = "payload")
    private String payload;

    @JsonProperty(value = "message_id")
    private String messageId;
}
