package com.fquick.timer.service.impl;

import com.fquick.timer.domain.model.Client;
import com.fquick.timer.domain.model.ClientRegistrationDetail;
import com.fquick.timer.dto.CreateClientDto;
import com.fquick.timer.dto.RegisterClientDto;
import com.fquick.timer.repository.ClientDetailRepository;
import com.fquick.timer.repository.ClientRepository;
import com.fquick.timer.service.ClientRegistrationDetailService;
import com.fquick.timer.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
@Slf4j
@Component("clientService")
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientRegistrationDetailService clientRegistrationDetailService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Client createClient(CreateClientDto clientDto) {
        Client client = new Client();
        client.setExternalId(clientDto.getClientExternalId());
        return clientRepository.save(client);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ClientRegistrationDetail registerClient(RegisterClientDto registerClientDto) {

        Client client = clientRepository.findByExternalId(registerClientDto.getClientExternalId());
        if (client == null) {
            client = new Client();
            client.setExternalId(registerClientDto.getClientExternalId());
            clientRepository.save(client);
        }
        return clientRegistrationDetailService.save(registerClientDto, client);
    }
}
