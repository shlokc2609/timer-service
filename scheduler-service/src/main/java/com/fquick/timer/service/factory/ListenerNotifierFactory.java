package com.fquick.timer.service.factory;

import com.fquick.timer.domain.enums.SubscriptionType;
import com.fquick.timer.service.ListenerNotifier;
import com.fquick.timer.service.impl.notifiers.VaradhiNotifier;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
public class ListenerNotifierFactory {
    @Autowired
    private VaradhiNotifier varadhiNotifier;

    public ListenerNotifier getNotifier(SubscriptionType subscriptionType)
    {
        if(subscriptionType == SubscriptionType.ASYNC_QUEUE || subscriptionType == SubscriptionType.ASYNC_TOPIC)
        {
            return varadhiNotifier;
        }
        return null;
    }

}
