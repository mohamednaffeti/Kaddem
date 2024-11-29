package org.example.kaddem.repositories;

import org.example.kaddem.entities.Equipe;
import org.example.kaddem.entities.Universite;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite,String> {

}
