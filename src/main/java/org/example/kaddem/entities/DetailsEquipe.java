package org.example.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kaddem.dtos.RequestEquipeDTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailsEquipe {
    @Id
    private String idDetailEquipe;
    private int salle;
    private String thematique;

    @OneToOne
    @JoinColumn(name = "id_equipe")
    @JsonBackReference
    private Equipe equipe;

    public static DetailsEquipe fromRequestEquipeDTOTODetailsEquipe(RequestEquipeDTO requestEquipeDTO){
        return DetailsEquipe.builder()
                .salle(requestEquipeDTO.getSalle())
                .thematique(requestEquipeDTO.getThematique())
                .build();
    }
}
