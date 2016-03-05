package com.fquick.timer.service.impl;

import com.fquick.timer.domain.model.Client;
import com.fquick.timer.domain.model.ClientRegistrationDetail;
import com.fquick.timer.dto.RegisterClientDto;
import com.fquick.timer.repository.ClientDetailRepository;
import com.fquick.timer.service.ClientRegistrationDetailService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
public class ClientRegistrationDetailServiceImpl implements ClientRegistrationDetailService{

    @Autowired
    private ClientDetailRepository clientDetailRepository;

    @Override
    public ClientRegistrationDetail save(RegisterClientDto registerClientDto, Client client) {
        ClientRegistrationDetail clientRegistrationDetail = new ClientRegistrationDetail();
        clientRegistrationDetail.setClient(client);
        clientRegistrationDetail.setClientUseCase(registerClientDto.getClientUseCase());
        clientRegistrationDetail.setIsActive(registerClientDto.getIsActive());
        clientRegistrationDetail.setUseCaseDescription(registerClientDto.getClientUseCaseDescription());
        clientRegistrationDetail.setSubscriptionType(registerClientDto.getSubscriptionType());
        clientRegistrationDetail.setSubscriptionDescription(registerClientDto.getSubscriptionDescription());
        return clientDetailRepository.save(clientRegistrationDetail);
    }
}
