package com.luan.clients.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luan.clients.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    List<Client> findAll(Pageable pageable);
}
