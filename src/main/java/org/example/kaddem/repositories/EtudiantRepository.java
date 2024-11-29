package org.example.kaddem.repositories;

import jakarta.transaction.Transactional;
import org.example.kaddem.entities.Departement;
import org.example.kaddem.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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


    @Query("select concat(e.nomE, ' ', e.prenomE) from Etudiant e inner join e.departement d " +
            "where d.nomDepartement = :deptName")
    List<String> getStudentByDepartement(@Param("deptName") String deptName);


    @Query(value = "select CONCAT(e.nome,' ',e.prenome), d.nom_departement,c.montant_contrat " +
            "from etudiant e " +
            "INNER JOIN departement d " +
            "ON e.departement_id = d.id_departement " +
            "INNER JOIN contrat c " +
            "ON e.id_etudiant = c.etudiant_id " +
            "where c.archive=0 ", nativeQuery = true)
    List<Object[]> getByDepartementandActiveContract();

    @Query(value = "select CONCAT(e.nome,' ',e.prenome), eqq.nom_equipe,dq.thematique, dq.salle " +
            "from etudiant e INNER JOIN etudiants_equipes eq ON e.id_etudiant = eq.etudiant_id " +
            "INNER JOIN equipe eqq ON eq.equipe_id = eqq.id_equipe " +
            "INNER JOIN details_equipe dq ON eqq.id_detail_equipe = dq.id_detail_equipe" , nativeQuery = true)
    List<Object[]> getdetailsEquipesForStudent();

    @Query("select e from Etudiant e inner join Departement d on e.departement.idDepartement = " +
            "d.idDepartement inner join Universite u on d.universite.idUniversite = u.idUniversite " +
            "where u.idUniversite = ?1")
    List<Etudiant> getStudentByUniversiy(String universityId);


}
