package com.luan.clients.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.luan.clients.model.Client;


public interface ClientRepository extends JpaRepository<Client, Long>{
}
