package org.example.kaddem.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.kaddem.dtos.RequestContratDTO;
import org.example.kaddem.enums.Specialite;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private LocalDate dateDebutContrat;
    private LocalDate dateFinContrat;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    private boolean archive;
    private int montantContrat;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;


     static public Contrat toContract(RequestContratDTO requestContratDTO){
         return Contrat.builder()
                 .dateDebutContrat(requestContratDTO.getDateDebutContrat())
                 .dateFinContrat(requestContratDTO.getDateFinContrat())
                 .specialite(requestContratDTO.getSpecialite())
                 .archive(requestContratDTO.isArchive())
                 .montantContrat(requestContratDTO.getMontantContrat())
                 .build();
     }
}
