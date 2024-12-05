package com.microservice.client.persistence.repository;

import com.microservice.client.persistence.models.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClienteRepository extends MongoRepository<Cliente, String> {
    Optional<Cliente> findByDniAndPassword(int dni, String password);
}
