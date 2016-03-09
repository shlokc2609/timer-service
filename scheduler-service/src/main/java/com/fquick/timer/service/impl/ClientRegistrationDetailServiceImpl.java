package com.fquick.timer.service.impl;

import com.fquick.timer.domain.model.Client;
import com.fquick.timer.domain.model.ClientRegistrationDetail;
import com.fquick.timer.domain.model.UseCase;
import com.fquick.timer.dto.RegisterClientDto;
import com.fquick.timer.repository.ClientDetailRepository;
import com.fquick.timer.repository.UseCaseRepository;
import com.fquick.timer.service.ClientRegistrationDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
@Slf4j
@Component("clientRegistrationDetailService")
public class ClientRegistrationDetailServiceImpl implements ClientRegistrationDetailService{

    @Autowired
    private ClientDetailRepository clientDetailRepository;

    @Autowired
    private UseCaseRepository useCaseRepository;

    @Override
    public ClientRegistrationDetail save(RegisterClientDto registerClientDto, Client client) {
        UseCase useCase = useCaseRepository.findByName(registerClientDto.getClientUseCase());
        if(useCase==null)
        {
            useCase = new UseCase();
            useCase.setUrl(registerClientDto.getUrl());
            useCase.setExchangeName(registerClientDto.getExchange_name());
            useCase.setName(registerClientDto.getClientUseCase());
            useCase.setMethod(registerClientDto.getMethod());
            useCase.setClientRegistrationDetails(new ArrayList<>());
        }
        ClientRegistrationDetail clientRegistrationDetail = new ClientRegistrationDetail();
        clientRegistrationDetail.setClient(client);
        clientRegistrationDetail.setUseCase(useCase);
        clientRegistrationDetail.setIsActive(registerClientDto.getIsActive());
        clientRegistrationDetail.setSubscriptionType(registerClientDto.getSubscriptionType());
        clientRegistrationDetail.setDescription(registerClientDto.getDescription());
        clientDetailRepository.save(clientRegistrationDetail);
        useCaseRepository.save(useCase);
        return clientRegistrationDetail;
    }
}
