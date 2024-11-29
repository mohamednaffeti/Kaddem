package org.example.kaddem.repositories;

import jakarta.transaction.Transactional;
import org.example.kaddem.entities.Contrat;
import org.example.kaddem.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface ContratRepository extends JpaRepository<Contrat,String> {

    @Modifying
    @Transactional
    @Query("UPDATE Contrat c set c.etudiant = ?1 where c.id=?2")
    void affectContractToStudent(Etudiant e , String idContract);

    @Query("select c.specialite,SUM(c.montantContrat) AS total_montant" +
            " from Contrat c where c.dateDebutContrat >= ?1 and " +
            "c.dateFinContrat<=?2 and c.archive=false " +
            "group by c.specialite")
    List<Object[]> findMontantParSpecialite(LocalDate dateDebut, LocalDate dateFin);


    @Query("select count(c) from Contrat c " +
            "where c.dateDebutContrat>= ?1 and c.dateFinContrat<= ?2 and c.archive=false ")
    long nbContratsValides (LocalDate dateDebut, LocalDate dateFin);


    @Query("select concat(e.nomE,' ',e.prenomE),c.specialite,c.montantContrat " +
            "from Etudiant e inner join Contrat c " +
            "ON e.idEtudiant = c.etudiant.idEtudiant " +
            "where c.archive=false " +
            "order by c.montantContrat desc ")
    List<Object[]> findActifContract();

}
