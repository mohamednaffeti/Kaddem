package org.example.kaddem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kaddem.entities.Equipe;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDetailsEquipeDTO {
    private String idDetailEquipe;
    private int salle;
    private String thematique;
    private Equipe equipe;


}
