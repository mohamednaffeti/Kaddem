package org.example.kaddem.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kaddem.entities.Contrat;
import org.example.kaddem.entities.Equipe;
import org.example.kaddem.enums.Specialite;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseContratDTO {
    private String id;
    private LocalDate dateDebutContrat;
    private LocalDate dateFinContrat;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    private boolean archive;
    private int montantContrat;
    private ResponseEtudiantDTO etudiant;

    public static ResponseContratDTO toResponseContratDTO(Contrat contrat){
        ResponseContratDTO responseContratDTO =  ResponseContratDTO.builder()
                .id(contrat.getId())
                .dateDebutContrat(contrat.getDateDebutContrat())
                .dateFinContrat(contrat.getDateFinContrat())
                .specialite(contrat.getSpecialite())
                .archive(contrat.isArchive())
                .montantContrat(contrat.getMontantContrat())
                .build();
        if(contrat.getEtudiant() != null){
            responseContratDTO.setEtudiant(ResponseEtudiantDTO.toEtudiantDTOResponse(contrat.getEtudiant()));
        }
        return responseContratDTO;
    }

}
