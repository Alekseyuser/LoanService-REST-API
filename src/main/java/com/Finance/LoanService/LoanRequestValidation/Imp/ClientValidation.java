package com.Finance.LoanService.LoanRequestValidation.Imp;

import com.Finance.LoanService.Entity.Client;
import com.Finance.LoanService.LoanRequestValidation.IClientValidation;
import com.Finance.LoanService.Repository.ClientRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Component
public class ClientValidation implements IClientValidation {

    private ClientRepository clientRepository;

    @Autowired
    public ClientValidation(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public boolean clientValidator(Client client){
        if(clientRepository.findById(client.getClientId()).isPresent()) {
            Client user = clientRepository.findById(client.getClientId()).get();
            return user.equals(client);
        }
        else{
            return false;
        }
    }
}
