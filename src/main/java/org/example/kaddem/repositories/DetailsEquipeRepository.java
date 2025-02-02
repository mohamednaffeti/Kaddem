package org.example.kaddem.repositories;

import org.example.kaddem.entities.DetailsEquipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface DetailsEquipeRepository extends JpaRepository<DetailsEquipe,String> {


    @Query("SELECT COUNT(d) from DetailsEquipe d")
    Long countRowsInDetailsEquipe();

}
