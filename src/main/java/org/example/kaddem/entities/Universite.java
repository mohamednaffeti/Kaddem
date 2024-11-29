package org.example.kaddem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kaddem.dtos.RequestUniversiteDTO;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Universite {
    @Id
    private String idUniversite;
    private String nomUniversite;

    @OneToMany(mappedBy = "universite", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Departement> departements;


    public static Universite fromRequestUniversiteDtoToUniversite(RequestUniversiteDTO requestUniversiteDTO){
        return Universite.builder()
                .nomUniversite(requestUniversiteDTO.getNomUniversite())
                .departements(new ArrayList<>())
                .build();
    }
}
