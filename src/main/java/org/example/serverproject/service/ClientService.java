package org.example.serverproject.service;

import org.example.serverproject.models.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAll();
    Client findOne(int id);
    void save(Client client);
    void deleteById(int id);
    Client findByName(String name);
    boolean checkAuthClient(Client client);
}
