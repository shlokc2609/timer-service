package com.fquick.timer.service.impl;

import com.fquick.timer.domain.model.Client;
import com.fquick.timer.domain.model.ClientRegistrationDetail;
import com.fquick.timer.dto.CreateClientDto;
import com.fquick.timer.dto.RegisterClientDto;
import com.fquick.timer.repository.ClientDetailRepository;
import com.fquick.timer.repository.ClientRepository;
import com.fquick.timer.service.ClientRegistrationDetailService;
import com.fquick.timer.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
public class ClientServiceImpl implements ClientService{
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientRegistrationDetailService clientRegistrationDetailService;

    @Override
    public Client createClient(CreateClientDto clientDto) {
        Client client = new Client();
        client.setClientExternalId(clientDto.getClientExternalId());
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public ClientRegistrationDetail registerClient(RegisterClientDto registerClientDto) {
        Client client = new Client();
        client.setClientExternalId(registerClientDto.getClientExternalId());
        return clientRegistrationDetailService.save(registerClientDto, client);
    }
}
