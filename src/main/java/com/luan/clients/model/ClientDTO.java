package com.luan.clients.model;

import java.time.LocalDate;
import java.time.Period;

public class ClientDTO {
    
    private Long id;
    private String name;
    private String email;
    private int cpf;
    private LocalDate birthDate;
    private int age;
    
    
    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.cpf = client.getCpf();
        this.birthDate = client.getBirthDate();
        this.age = calcularIdade(this.birthDate);
    }

    private static int calcularIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getCpf() {
        return cpf;
    }
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    
}
