package org.example.serverproject.serviceImpl;

import org.example.serverproject.models.Client;
import org.example.serverproject.models.Product;
import org.example.serverproject.repositories.ClientRepo;
import org.example.serverproject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {
    private final ClientRepo clientRepo;

    @Autowired
    public ClientServiceImpl(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public List<Client> findAll() {
        return clientRepo.findAll();
    }

    @Override
    public Client findOne(int id) {
        return clientRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Client client) {
        client.setProductList(new ArrayList<>());
        clientRepo.save(client);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        clientRepo.deleteById(id);
    }

    @Override
    public Client findByName(String name) {
        return clientRepo.findClientByName(name).orElse(null);
    }

    @Override
    public boolean checkAuthClient(Client client) {
        Client currClient = findByName(client.getName());
        if (currClient != null) {
            return currClient.getPassword().equals(client.getPassword());
        }
        return false;
    }

    public List<Product> getProductListByClientId(int id){
        Client client = clientRepo.findById(id).orElse(null);
        if(client != null){
           return client.getProductList();
        }
        return new ArrayList<>();
    }
}
