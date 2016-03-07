package com.fquick.timer.service.impl.notifiers;

import com.fquick.timer.dto.NotifierDto;
import com.fquick.timer.dto.VaradhiNotifierDto;
import com.fquick.timer.service.ListenerNotifier;
import org.springframework.stereotype.Component;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
@Component("varadhiNotifier")
public class VaradhiNotifier implements ListenerNotifier {
    @Override
    public void notify(NotifierDto notifierDto) {
        VaradhiNotifierDto varadhiNotifierDto = (VaradhiNotifierDto) notifierDto;
    }
}
