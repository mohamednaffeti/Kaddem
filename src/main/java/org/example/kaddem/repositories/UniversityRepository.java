package org.example.kaddem.repositories;

import org.example.kaddem.entities.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<Universite,String> {
}
