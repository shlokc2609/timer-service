package com.fquick.timer.service;

import com.fquick.timer.dto.NotifierDto;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
public interface ListenerNotifier {
    void notify(NotifierDto notifierDto);
}
