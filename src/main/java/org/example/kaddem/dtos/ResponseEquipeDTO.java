package org.example.kaddem.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kaddem.entities.DetailsEquipe;
import org.example.kaddem.entities.Equipe;
import org.example.kaddem.entities.Etudiant;
import org.example.kaddem.enums.Niveau;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseEquipeDTO {
    private String idEquipe;
    private String nomEquipe;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;
    private DetailsEquipe detailsEquipe;
    private List<Etudiant> etudiants;

    public static ResponseEquipeDTO fromEntityToResponseEquipeDTO(Equipe equipe){
        return ResponseEquipeDTO.builder()
                .idEquipe(equipe.getIdEquipe())
                .nomEquipe(equipe.getNomEquipe())
                .niveau(equipe.getNiveau())
                .detailsEquipe(equipe.getDetailsEquipe())
                .etudiants(equipe.getEtudiants())
                .build();
    }

}
