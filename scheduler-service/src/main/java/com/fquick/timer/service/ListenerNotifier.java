package com.fquick.timer.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fquick.timer.domain.model.ClientRegistrationDetail;
import com.fquick.timer.domain.model.Job;
import com.fquick.timer.dto.NotifierDto;
import com.fquick.timer.exception.HeaderMappingFailedException;
import com.fquick.timer.exception.NotificationFailedException;

import java.io.IOException;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
public interface ListenerNotifier {
    void notify(NotifierDto notifierDto) throws NotificationFailedException;
    NotifierDto getMappingDto(Job job, ClientRegistrationDetail clientRegistrationDetail) throws HeaderMappingFailedException;
}
