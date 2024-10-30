package org.example.kaddem.repositories;

import jakarta.transaction.Transactional;
import org.example.kaddem.entities.Departement;
import org.example.kaddem.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,String> {

    @Modifying
    @Transactional
    @Query("update Etudiant e set e.departement = ?1 where e.idEtudiant = ?2")
    void assignEtudiantToDepartement(Departement departement,String etudiantId);


    @Query("SELECT e FROM Etudiant e WHERE e.nomE = ?1 AND e.prenomE = ?2")
    Optional<Etudiant> getByFullName(String nomE , String prenomE);

    Optional<Etudiant> getFirstByNomEAndPrenomE(String nomE , String prenomE);

    @Transactional
    @Query("select e from Etudiant e inner join Departement d ON e.departement.idDepartement = d.idDepartement where d.idDepartement= ?1")
    List<Etudiant> getAllByDepartement(String idDepartement);





}
