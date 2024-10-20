package org.example.kaddem.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.kaddem.dtos.RequestEtudiantDTO;
import org.example.kaddem.dtos.ResponseEtudiantDTO;
import org.example.kaddem.enums.Option;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Etudiant {
    @Id
    private String idEtudiant;
    private String prenomE;
    private String nomE;
    @Enumerated(EnumType.STRING)
    private Option option;

    @OneToMany(mappedBy = "etudiant",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Contrat> contrats;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "etudiants_equipes",
            joinColumns = @JoinColumn(name = "etudiant_id"),
            inverseJoinColumns = @JoinColumn(name = "equipe_id")
    )
    private List<Equipe> equipes = new ArrayList<>(); // à tester lfaza taa add directement mel code tetzed fel base de données

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departement_id")
    private Departement departement;
    public static Etudiant toEtudiantFromRequest(RequestEtudiantDTO etudiantDTO){
        return Etudiant.builder()
                .prenomE(etudiantDTO.getPrenomE())
                .nomE(etudiantDTO.getNomE())
                .option(etudiantDTO.getOption())
                .build();
    }
    public static Etudiant toEtudiantFromResponse(ResponseEtudiantDTO etudiantDTO){
        return Etudiant.builder()
                .idEtudiant(etudiantDTO.getIdEtudiant())
                .prenomE(etudiantDTO.getPrenomE())
                .nomE(etudiantDTO.getNomE())
                .option(etudiantDTO.getOption())
                .build();
    }

}
