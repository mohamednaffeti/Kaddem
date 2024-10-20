package org.example.kaddem.repositories;

import org.example.kaddem.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository<Departement,String> {

//understand pagination and sorting
}
