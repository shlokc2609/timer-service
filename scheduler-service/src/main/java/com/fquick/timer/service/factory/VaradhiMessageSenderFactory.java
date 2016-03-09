package com.fquick.timer.service.factory;

import com.flipkart.restbus.client.core.MessageSender;
import com.fquick.timer.domain.enums.SubscriptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by shlok.chaurasia on 08/03/16.
 */
@Component("varadhiMessageSenderFactory")
public class VaradhiMessageSenderFactory {
    private final MessageSender asyncMessageSender;

    @Autowired
    public VaradhiMessageSenderFactory(@Qualifier("async") MessageSender asyncMessageSender) {
        this.asyncMessageSender = asyncMessageSender;
    }

    public MessageSender getMessageSender(SubscriptionType subscriptionType)
    {
        if(subscriptionType.equals(SubscriptionType.ASYNC_QUEUE) || subscriptionType.equals(SubscriptionType.ASYNC_TOPIC))
            return asyncMessageSender;
        return null;
    }
}
