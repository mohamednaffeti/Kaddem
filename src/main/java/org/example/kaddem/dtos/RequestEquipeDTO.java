package org.example.kaddem.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kaddem.enums.Niveau;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestEquipeDTO {
    private String nomEquipe;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;
    private int salle;
    private String thematique;

}
