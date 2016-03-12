package com.fquick.timer.service;

import com.fquick.timer.domain.model.Client;
import com.fquick.timer.domain.model.ClientRegistrationDetail;
import com.fquick.timer.dto.RegisterClientDto;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
public interface ClientRegistrationDetailService {
    ClientRegistrationDetail save(RegisterClientDto registerClientDto, Client client);
}
