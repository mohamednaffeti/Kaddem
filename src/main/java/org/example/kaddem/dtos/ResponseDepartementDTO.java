package org.example.kaddem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kaddem.entities.Departement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDepartementDTO {

    private String idDepartement;
    private String nomDepartement;

    public static ResponseDepartementDTO fromDeptToResponseDepartementDTO(Departement departement){
        return ResponseDepartementDTO.builder()
                .idDepartement(departement.getIdDepartement())
                .nomDepartement(departement.getNomDepartement())
                .build();
    }
}
