package com.Finance.LoanService.Service.Imp;

import com.Finance.LoanService.Entity.Client;
import com.Finance.LoanService.Repository.ClientRepository;
import com.Finance.LoanService.Service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ClientService implements IClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }


    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }


}
