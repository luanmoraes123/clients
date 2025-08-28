package com.luan.clients.service;

import java.time.LocalDate;
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

    public Client create(String name, String email, int cpf, LocalDate birthDate){
        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setCpf(cpf);
        client.setBirthDate(birthDate);
        return clientRepository.save(client);
    }

    public Client findById(Long id) throws ResponseStatusException{
        return clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

    }

    public Client update(Long id, Client clientUpdated){
        return clientRepository.findById(id).map(
            client -> {
            client.setName(clientUpdated.getName());
            client.setEmail(clientUpdated.getEmail());
            client.setBirthDate(clientUpdated.getBirthDate());
            client.setCpf(clientUpdated.getCpf());
            return clientRepository.save(client);
    }).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public void delete(Long id){
        clientRepository.deleteById(id);
    }
}
