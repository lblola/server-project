package org.example.serverproject.repositories;

import org.example.serverproject.models.Admin;
import org.example.serverproject.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
    Optional<Client> findClientByName(String name);

}
