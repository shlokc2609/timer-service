package com.fquick.timer.service.impl.notifiers;

import com.fquick.timer.dto.NotifierDto;
import com.fquick.timer.dto.VaradhiNotifierDto;
import com.fquick.timer.service.ListenerNotifier;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
public class VaradhiNotifier implements ListenerNotifier {
    @Override
    public void notify(NotifierDto notifierDto) {
        VaradhiNotifierDto varadhiNotifierDto = (VaradhiNotifierDto) notifierDto;
    }
}
