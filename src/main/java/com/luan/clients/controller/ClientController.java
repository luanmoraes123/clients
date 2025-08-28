package com.luan.clients.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luan.clients.model.ClientDTO;
import com.luan.clients.service.ClientService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/clients")
public class ClientController {
    
    private ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> list(@RequestParam String page) {
        return clientService.getAll().stream()
        .map(client -> new ClientDTO(client))
        .collect(Collectors.toList());
    }
    
    @GetMapping("id")
    public ClientDTO getMethodName(@PathVariable("id") Long id) {
       return new ClientDTO(clientService.findById(id));
    }
    

    @DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        clientService.delete(id);
    }


    
}
