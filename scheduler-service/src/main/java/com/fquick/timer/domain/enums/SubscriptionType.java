package com.fquick.timer.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
@AllArgsConstructor
public enum SubscriptionType {
    SYNC("sync"),
    ASYNC_QUEUE("queue"),
    ASYNC_TOPIC("topic");
    @Getter
    private String subscriptionType;
}
