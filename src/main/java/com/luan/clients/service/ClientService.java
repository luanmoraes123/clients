package com.luan.clients.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.luan.clients.model.Client;
import com.luan.clients.repository.ClientRepository;;

@Service
public class ClientService {
    
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client create(Client client){
        return clientRepository.save(client);
    }

    public Client findById(Long id) throws ResponseStatusException{
        return clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

    }

    public Client update(Client client){
        return clientRepository.save(client);
    }

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public void delete(Long id){
        clientRepository.deleteById(id);
    }
}
