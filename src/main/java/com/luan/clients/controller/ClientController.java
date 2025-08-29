package com.luan.clients.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luan.clients.model.Client;
import com.luan.clients.model.ClientDTO;
import com.luan.clients.service.ClientService;

import ch.qos.logback.core.joran.conditional.IfAction;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/clients")
public class ClientController {
    
    private ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> list(@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer itens) {
        if (page != null && itens != null) {        
            return clientService.getAll(PageRequest.of(page, itens)).stream()
            .map(client -> new ClientDTO(client))
            .collect(Collectors.toList());
        }
        return clientService.findAll().stream()
            .map(client -> new ClientDTO(client))
            .collect(Collectors.toList());
    }
    
    @GetMapping("id")
    public ClientDTO getOne(@PathVariable("id") Long id) {
       return new ClientDTO(clientService.findById(id));
    }
    

    @DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        clientService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO create(@RequestBody Client client){
        return new ClientDTO(clientService.create(client));
    }

    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable("id") Long id, Client client){
        return new ClientDTO(clientService.update(id, client));
    }
}
