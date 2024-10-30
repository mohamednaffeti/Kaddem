package org.example.kaddem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kaddem.dtos.RequestDepartementDTO;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Departement {
    @Id
    private String idDepartement;
    private String nomDepartement;

    @OneToMany (mappedBy = "departement",fetch = FetchType.EAGER)
    private List<Etudiant> etudiants;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private Universite universite;



    public static Departement fromDTORequestToDepartement(RequestDepartementDTO requestDepartementDTO){
        return Departement.builder()
                .nomDepartement(requestDepartementDTO.getNomDepartement())
                .build();
    }
}
