package org.example.kaddem.repositories;

import jakarta.transaction.Transactional;
import org.example.kaddem.entities.Departement;
import org.example.kaddem.entities.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface DepartementRepository extends JpaRepository<Departement,String> {

//understand pagination and sorting
    @Modifying
    @Transactional
    @Query("update Departement d set d.universite = ?1 where d.idDepartement = ?2")
    void assignUniversiteToDepartement (Universite universite,String departementId);

    @Query("select d from Departement d inner join Universite u on d.universite.idUniversite = u.idUniversite " +
            "where d.universite.idUniversite=?1")
    List<Departement> getdepartementsByUniv(String idUniversite);


    @Query("select d.nomDepartement, count(e) from Etudiant e INNER JOIN Departement d" +
            " ON e.departement.idDepartement = d.idDepartement" +
            " group by d.nomDepartement")
    List<Object[]> groupeByDepartement();



}
