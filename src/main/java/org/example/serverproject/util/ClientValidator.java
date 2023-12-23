package org.example.serverproject.util;

import org.example.serverproject.models.Client;
import org.example.serverproject.serviceImpl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClientValidator implements Validator {
    private final ClientServiceImpl clientService;

    @Autowired
    public ClientValidator(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;

        if(clientService.findByName(client.getName()) != null){
            errors.rejectValue("name","", "Сенсор с таким именем уже есть");
        }
    }
}
