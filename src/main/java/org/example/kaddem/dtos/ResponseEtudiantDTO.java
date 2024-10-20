package org.example.kaddem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kaddem.entities.Etudiant;
import org.example.kaddem.enums.Option;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseEtudiantDTO {

    private String idEtudiant;
    private String prenomE;
    private String nomE;
    private Option option;


    public static ResponseEtudiantDTO toEtudiantDTOResponse(Etudiant etudiant){
        return ResponseEtudiantDTO.builder()
                .idEtudiant(etudiant.getIdEtudiant())
                .prenomE(etudiant.getPrenomE())
                .nomE(etudiant.getNomE())
                .option(etudiant.getOption())
                .build();
    }
}
