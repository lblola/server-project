package org.example.serverproject.repositories;

import org.example.serverproject.models.Admin;
import org.example.serverproject.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {
    Optional<Admin> findAdminByName(String name);

}
