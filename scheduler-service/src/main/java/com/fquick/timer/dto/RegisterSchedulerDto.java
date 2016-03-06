package com.fquick.timer.dto;

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
    private String clientExternalId;

    @NotNull
    private DateTime scheduledTime;

    @NotNull
    private String useCase;

    private String payload;


    private String messageId;

}
