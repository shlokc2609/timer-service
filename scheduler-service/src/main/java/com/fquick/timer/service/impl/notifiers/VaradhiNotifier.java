package com.fquick.timer.service.impl.notifiers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.restbus.client.core.MessageSender;
import com.flipkart.restbus.client.entity.Event;
import com.fquick.timer.Constants;
import com.fquick.timer.domain.enums.SubscriptionType;
import com.fquick.timer.domain.model.ClientRegistrationDetail;
import com.fquick.timer.domain.model.Job;
import com.fquick.timer.domain.model.JobAttribute;
import com.fquick.timer.domain.model.UseCase;
import com.fquick.timer.dto.NotifierDto;
import com.fquick.timer.dto.VaradhiNotifierDto;
import com.fquick.timer.exception.HeaderMappingFailedException;
import com.fquick.timer.exception.NotificationFailedException;
import com.fquick.timer.service.ListenerNotifier;
import com.fquick.timer.service.factory.VaradhiMessageSenderFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
@Component("varadhiNotifier")
@Slf4j
public class VaradhiNotifier implements ListenerNotifier {

    @Autowired
    private VaradhiMessageSenderFactory varadhiMessageSenderFactory;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Value("${restBusConfig.restEnvName}")
    private String restEnvName;

    @Override
    public void notify(NotifierDto notifierDto) throws NotificationFailedException{
        Event event = getEvent((VaradhiNotifierDto) notifierDto);
        MessageSender messageSender = varadhiMessageSenderFactory.getMessageSender(notifierDto.getSubscriptionType());
        try {
            messageSender.publish(event);
        } catch (IOException e) {
            throw new NotificationFailedException(((VaradhiNotifierDto) notifierDto).toString());
        }
    }

    //add Header
    public NotifierDto getMappingDto(Job job, ClientRegistrationDetail clientRegistrationDetail) throws HeaderMappingFailedException {
        List<JobAttribute> jobAttributeList = job.getJobAttributes();
        Map<String, List<JobAttribute>> jobAttributes = getMappingAttributeName(jobAttributeList);
        UseCase useCase = clientRegistrationDetail.getUseCase();
        VaradhiNotifierDto notifierDto = new VaradhiNotifierDto();
        notifierDto.setUri(useCase.getUrl());
        notifierDto.setHttpMethod(useCase.getMethod());
        notifierDto.setExchangeName(useCase.getExchangeName());
        notifierDto.setSubscriptionType(clientRegistrationDetail.getSubscriptionType());
        if(jobAttributes.containsKey(Constants.GROUP_ID))
            notifierDto.setGroupId(jobAttributes.get(Constants.GROUP_ID).get(0).getAttributeValue());
        if(jobAttributes.containsKey(Constants.PAYLOAD))
            notifierDto.setPayload(jobAttributes.get(Constants.PAYLOAD).get(0).getAttributeValue());
        String headerString = useCase.getHeader();
        Map<String, String> headers = null;
        if(headerString!=null)
        {
        try {
            headers = mapper.readValue(headerString, Map.class);
        }
        catch(IOException ex)
        {
            throw new HeaderMappingFailedException();
        }
        }
        notifierDto.setHeaders(headers);
        return notifierDto;
    }

    private Event getEvent(VaradhiNotifierDto varadhiNotifierDto) {
        Event event = new Event(Constants.VARADHI_EVENT_NAME, varadhiNotifierDto.getPayload());
        event.setHttpUri(varadhiNotifierDto.getUri());
        event.setHttpMethod(varadhiNotifierDto.getHttpMethod());
        event.setExchangeName(restEnvName + varadhiNotifierDto.getExchangeName());
        if (varadhiNotifierDto.getSubscriptionType().equals(SubscriptionType.ASYNC_QUEUE))
            event.setExchangeType(Constants.VARADHI_QUEUE);
        else
            event.setExchangeType(Constants.VARADHI_TOPIC);
        event.setGroupId(varadhiNotifierDto.getGroupId());
        event.setHeaders(varadhiNotifierDto.getHeaders());
        event.setPayload(varadhiNotifierDto.getPayload());
        return event;
    }

    private Map<String, List<JobAttribute>> getMappingAttributeName(List<JobAttribute> jobAttributes) {
        return jobAttributes.stream().collect(Collectors.groupingBy(JobAttribute::getAttributeName));
    }
}
