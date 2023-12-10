package org.example.serverproject.repositories;

import org.example.serverproject.models.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepo extends JpaRepository<Passport, Integer> {
}
