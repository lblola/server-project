package org.example.serverproject.util;

import org.example.serverproject.DTO.ClientAuthDTO;
import org.example.serverproject.models.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ClientMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public ClientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClientAuthDTO toDto(Client client){
        return Objects.isNull(client) ? null : modelMapper.map(client, ClientAuthDTO.class);
    }

    public Client toEntity(ClientAuthDTO clientAuthDTO){
        return Objects.isNull(clientAuthDTO) ? null : modelMapper.map(clientAuthDTO, Client.class);
    }
}
