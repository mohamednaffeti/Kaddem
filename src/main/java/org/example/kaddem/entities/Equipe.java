package org.example.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kaddem.dtos.RequestEquipeDTO;
import org.example.kaddem.enums.Niveau;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipe {
    @Id
    private String idEquipe;
    private String nomEquipe;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;
    @ManyToMany(mappedBy = "equipes",fetch = FetchType.EAGER)
    private List<Etudiant> etudiants = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detail_equipe")
    @JsonManagedReference
    private DetailsEquipe detailsEquipe;

    public static Equipe fromRequestDTOtoEntity(RequestEquipeDTO requestEquipeDTO){
        return Equipe.builder()
                .nomEquipe(requestEquipeDTO.getNomEquipe())
                .niveau(requestEquipeDTO.getNiveau())
                .build();
    }
}
