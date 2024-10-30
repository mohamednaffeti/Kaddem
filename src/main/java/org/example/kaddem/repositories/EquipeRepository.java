package org.example.kaddem.repositories;

import org.example.kaddem.entities.Equipe;
import org.example.kaddem.entities.Universite;
import org.example.kaddem.enums.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface EquipeRepository extends JpaRepository<Equipe,String> {

    @Query("select e from Equipe e where e.niveau = :niveau")
    List<Equipe> findWithNiveau(@Param("niveau") Niveau niveau);
}
